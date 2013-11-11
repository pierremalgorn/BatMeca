package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import service.SubMaterialService;
import service.TestService;
import service.manager.ServiceManager;
import entity.SubMaterial;
import entity.Test;

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
    private SubMaterialService subMaterialService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
        subMaterialService = ServiceManager.INSTANCE.getSubMaterialService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("idSub", request.getParameter("idSub"));
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addTest.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("inputNameTest");
		
		String id = request.getParameter("idSub");
		System.out.println("ID  = "+id);
		SubMaterial sub = subMaterialService.find(Integer.parseInt(id));
		
		Test test = new Test();
		test.setName(name);
		test.setDate(new Date());
		test.setSub(sub);
		testService.add(test);
		
		String savePath = "C:"+ File.separator +"Users"+ File.separator +"max"+ File.separator+"Ressources";
		
		 for (Part part : request.getParts()) {
	            String fileName = extractFileName(part);
	            System.out.println("FILE NAME = "+fileName);
	            //part.write(savePath + File.separator + fileName);
	            part.write(fileName);
	        }
		
		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/IndexTest?idSub="+id));
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
