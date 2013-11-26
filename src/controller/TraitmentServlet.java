package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String action = request.getParameter("action");
		if(action != null && action.compareTo("lisser") == 0){
			csv.lissageOrdre2(f.getPathSave(t)+"/data.csv", f.getPathSave(t)+"/lissage.csv");
			String data = csv.readAll(f.getPathSave(t)+"/lissage.csv");
			response.getWriter().write(data);
		}
		String cut = request.getParameter("cut");
		if( cut!= null){
			System.out.println("CUT = "+cut);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
