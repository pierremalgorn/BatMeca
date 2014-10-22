package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Test;
import service.TestService;
import service.manager.ServiceManager;

/**
 * Permet de réaliser des traitements sur une courbe
 * Servlet implementation class TraitmentServlet
 */
@WebServlet("/Traitment")
public class TraitmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitmentServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idTest = Integer.parseInt(request.getParameter("id"));
		Test t = testService.find(idTest);
		ServletContext context = getServletContext();
		CsvHandler csv = new CsvHandler(context.getInitParameter("root")+"/"+context.getInitParameter("name"));
		
		FolderHandler f = new FolderHandler(context.getInitParameter("ressourcePath"));
		//Réalisation du lissage
		String lisser = request.getParameter("lisser");
		if(lisser != null ){
			String file = request.getParameter("file");
			f.addDataHistoryFile("Lisser Data;file "+file, t);
			csv.lissageOrdre2(file, f.getPathSave(t)+"/curve/outputLissageTmp.csv");
			//csv.lissageOrdre2(f.getPathSave(t)+"/dataInput.csv", f.getPathSave(t)+"/dataOutput.csv");
			String data = csv.readAll(f.getPathSave(t)+"/curve/outputLissageTmp.csv");
			f.renameFile(f.getPathSave(t)+"/curve/outputLissageTmp.csv", file);
			response.getWriter().write(data);
		}
		//Réalisation d'une coupe de courbe
		String cut = request.getParameter("cut");
		if( cut!= null){
			String after = request.getParameter("after");
			String before = request.getParameter("before");
			//int end = Integer.parseInt(request.getParameter("end"));
			String file = request.getParameter("file");
			if(after != null){
				int start = Integer.parseInt(request.getParameter("start"));
				try {
					csv.cutAfter(start, file);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				f.addDataHistoryFile("Cut After:"+start+";file "+file, t);
			}else if(before != null){
				int end = Integer.parseInt(request.getParameter("end"));
				try {
					csv.cutBefore(end, file);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				f.addDataHistoryFile("Cut Before:"+end+";file "+file+";", t);
			}
			
			String data = csv.readAll(file);
			
			response.getWriter().write(data);
		}
		//Calcule du max d'une colonne
		String calMax = request.getParameter("calMax");
		if( calMax!= null){
			String file = request.getParameter("file");
			float max = 0 ;
			try {
				max = csv.maxValueColumn(Integer.parseInt(calMax),file);
			} catch (NumberFormatException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f.addResult("MAX FILE:"+file+";max="+max+";", t);
			f.addDataHistoryFile("MAX FILE:"+file+";calMax:"+calMax+";", t);
			response.getWriter().write(new Gson().toJson(max));
		}
		//Multiplication par un facteur
		String factor = request.getParameter("factor");
		if( factor!= null){

			f.addDataHistoryFile("factor FILE", t);
		}
		/*
		 * Reset de la courbes
		 * */
		String reset = request.getParameter("reset");
		if(reset != null){
			//csv.datToCsv(f.getPathSave(t)+"/data/"+f.getFileNameData(t), f.getPathSave(t)+"/dataInput.csv");
			String data = csv.readAll(f.getPathSave(t)+"/dataInput.csv");
			System.out.println("RESET = "+reset);
			f.addDataHistoryFile("RESET FILE", t);
			response.getWriter().write(data);
		}
		
		String coef = request.getParameter("coef");
		if(coef!= null){
			String file = request.getParameter("file");
			f.addDataHistoryFile("CALCUL COEFF:FILE "+file, t);
			f.addResult("COEF="+coef+";File="+file+";", t);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idTest = Integer.parseInt(request.getParameter("inputId"));
		float factor = Float.parseFloat(request.getParameter("inputFactor"));
		int nbColumn = Integer.parseInt(request.getParameter("selectRow"));
		String file = request.getParameter("file");
		ServletContext context = getServletContext();
		CsvHandler csv = new CsvHandler(context.getInitParameter("root")+"/"+context.getInitParameter("name"));
		
		FolderHandler f = new FolderHandler(context.getInitParameter("ressourcePath"));
		Test test = testService.find(idTest);
		String[] tab = file.split("\\.");
	
		System.out.println("size  :"+tab.length);
		tab = tab[0].split("/");
		tab = tab[tab.length -1].split("-");
		int other = 0;
		if(nbColumn == Integer.parseInt(tab[0])){
			nbColumn = 1;
			other = 2;
		}else{
			nbColumn = 2;
			other =1;
		}
		try {
			csv.factorColumn(nbColumn, other,factor, file,f.getPathSave(test)+"/curve/factorcurve.csv" );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		f.addDataHistoryFile("FACTOR:"+factor+";FILE:"+file+";nbColumn:"+nbColumn, test);
		String data = csv.readAll(f.getPathSave(test)+"/curve/factorcurve.csv");
		f.renameFile(f.getPathSave(test)+"/curve/factorcurve.csv", file);
		response.getWriter().write(data);
		
		
		
		
		
	}

}
