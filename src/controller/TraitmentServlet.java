package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.IOException;

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
		CsvHandler csv = new CsvHandler();
		FolderHandler f = new FolderHandler();
		//Réalisation du lissage
		String lisser = request.getParameter("lisser");
		if(lisser != null ){
			f.addDataHistoryFile("Lisser Data", t);
			String file = request.getParameter("file");
			
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
				csv.cutAfter(start, file);
			}else if(before != null){
				int end = Integer.parseInt(request.getParameter("end"));
				csv.cutBefore(end, file);
			}
			
			//csv.deletePortionCsv(file, start, end);
			String data = csv.readAll(file);
			System.out.println("CUT = "+cut);
			f.addDataHistoryFile("CUT FILE", t);
			response.getWriter().write(data);
		}
		//Calcule du max d'une colonne
		String calMax = request.getParameter("calMax");
		if( calMax!= null){
			String file = request.getParameter("file");
			float max = csv.maxValueColumn(Integer.parseInt(calMax),file);
			
			f.addDataHistoryFile("MAX FILE", t);
			response.getWriter().write(new Gson().toJson(max));
		}
		//Multiplication par un facteur
		String factor = request.getParameter("factor");
		if( factor!= null){
			System.out.println("factor = "+factor);
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idTest = Integer.parseInt(request.getParameter("inputId"));
		float factor = Float.parseFloat(request.getParameter("inputFactor"));
		int nbColumn = Integer.parseInt(request.getParameter("selectRow"));
		String file = request.getParameter("file");
		System.out.println("+++++++++++++++++++++++++++++++++");
		System.out.println("ID TEST = "+idTest);
		System.out.println("FACTOR = "+factor);
		System.out.println("NB COLUMN = "+nbColumn);
		System.out.println("FILE "+file);
		CsvHandler csv = new CsvHandler();
		FolderHandler f = new FolderHandler();
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
		csv.factorColumn(nbColumn, other,factor, file,f.getPathSave(test)+"/curve/factorcurve.csv" );
		
		
		
		String data = csv.readAll(f.getPathSave(test)+"/curve/factorcurve.csv");
		f.renameFile(f.getPathSave(test)+"/curve/factorcurve.csv", file);
		response.getWriter().write(data);
		
		
		
		
		
	}

}
