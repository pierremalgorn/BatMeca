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
		String cut = request.getParameter("cut");
		if( cut!= null){
			int start = Integer.parseInt(request.getParameter("start"));
			int end = Integer.parseInt(request.getParameter("end"));
			csv.deletePortionCsv(f.getPathSave(t)+"/dataInput.csv", start, end);
			String data = csv.readAll(f.getPathSave(t)+"/dataInput.csv");
			System.out.println("CUT = "+cut);
			f.addDataHistoryFile("CUT FILE", t);
			response.getWriter().write(data);
		}
		
		String calMax = request.getParameter("calMax");
		if( calMax!= null){
			float max = csv.maxValueColumn(Integer.parseInt(calMax),f.getPathSave(t)+"/dataInput.csv");
			
			f.addDataHistoryFile("MAX FILE", t);
			response.getWriter().write(new Gson().toJson(max));
		}
		String factor = request.getParameter("factor");
		if( factor!= null){
			System.out.println("factor = "+factor);
			f.addDataHistoryFile("factor FILE", t);
		}
		
		String reset = request.getParameter("reset");
		if(reset != null){
			csv.datToCsv(f.getPathSave(t)+"/data/"+f.getFileNameData(t), f.getPathSave(t)+"/dataInput.csv");
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
		System.out.println("+++++++++++++++++++++++++++++++++");
		System.out.println("ID TEST = "+idTest);
		System.out.println("FACTOR = "+factor);
		System.out.println("NB COLUMN = "+nbColumn);
		
		CsvHandler csv = new CsvHandler();
		FolderHandler f = new FolderHandler();
		Test test = testService.find(idTest);
		
		csv.factorColumn(nbColumn, factor,f.getPathSave(test)+"/dataInput.csv",f.getPathSave(test)+"/dataOutput.csv");
		
		String data = csv.readAll(f.getPathSave(test)+"/dataOutput.csv");
		f.renameCsvOutput(test);
		response.getWriter().write(data);
		
		
		
		
		
	}

}
