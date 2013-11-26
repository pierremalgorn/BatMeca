package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import service.MaterialService;
import service.TestService;
import service.TypeMaterialAttributService;
import service.TypeTestAttributService;
import service.manager.ServiceManager;
import entity.Material;
import entity.Test;
import entity.TestAttribute;
import entity.TypeTestAttribute;
import entity.User;

/**
 * Servlet implementation class AddTestServlet
 */
@WebServlet("/AddTest")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB 
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class AddTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;    
    private MaterialService materialService;
    //private TypeMaterialAttributService typeMatService;
    private TypeTestAttributService typeTestService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
        materialService = ServiceManager.INSTANCE.getMaterialService();
       //typeMatService = ServiceManager.INSTANCE.getTypeMaterialAttributService();
       typeTestService = ServiceManager.INSTANCE.getTypeTestAttributService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TypeTestAttribute> typesTest;
		typesTest = typeTestService.findAll();
		
		request.setAttribute("typesTest", typesTest);
		request.setAttribute("idMat", request.getParameter("idMat"));
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addTest.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("inputNameTest");
		List<TypeTestAttribute> typesTest;
		typesTest = typeTestService.findAll();
		
		/**
		 * Recuperation du materiel associé
		 */
		Material mat = materialService.find(Integer.parseInt(request.getParameter("idMat")));
		CsvHandler csv = new CsvHandler();
		
		Test test = new Test();
		test.setName(name);
		test.setDate(new Date());
		test.setMaterial(mat);
		
		//Ajout des attributs
		
		test.setTestAttributs(new HashSet<TestAttribute>());
		for (TypeTestAttribute typeAttr : typesTest) {
			TestAttribute testAttr = new TestAttribute();
			testAttr.setValue(request.getParameter("input"+typeAttr.getName()));
			testAttr.setTest(test);
			testAttr.setTypeTestAttr(typeAttr);
			test.addTestAttribute(testAttr);
		}
		
		HttpSession session = request.getSession();
		test.setUser((User) session.getAttribute("sessionUser"));
		
		//Enregistrement en base de données
		testService.add(test);
		//Création de repertoire associer
		FolderHandler f = new FolderHandler();
		f.initDirectory(test);
		String savePath = f.getPathSave(test);
		//Upload des fichiers
		int cpt = 0;
		 for (Part part : request.getParts()) {
	            String fileName = extractFileName(part);
	            System.out.println("FILE NAME = "+fileName);
	            
	            if(fileName.compareTo("") != 0){
	            	if(cpt == 0){
	            		part.write(savePath + File.separator +"data/"+fileName);
	            		cpt ++;
	            	}else{
	            		part.write(savePath + File.separator +"config/"+ fileName);
	            	}
	            	/*if(fileName.matches("dat")){
	            		part.write(savePath + File.separator + "data/"+fileName);
	            	}else if(fileName.matches("par")){
	            		part.write(savePath + File.separator +"config/"+ fileName);
	            	}else{
	            		System.out.println("ERROR INVALID FILE");
	            	}*/
	            	
	            }
	            
	            //part.write(fileName);
	        }
		 //Conversion du fichier data en csv
		csv.datToCsv(f.getPathSave(test)+"/data/"+f.getFileNameData(test), f.getPathSave(test)+"/data.csv");
		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/ShowTest?idTest="+test.getId()));
	}
	
	 /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
