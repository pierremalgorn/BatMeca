package controller;

import handler.CsvHandler;
import handler.RuntimeHandler;

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
 * Servlet implementation class ExecTestServlet
 */
@WebServlet("/ExecTest")
public class ExecTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int id = Integer.parseInt(request.getParameter("idTest"));
//		Test test = testService.find(id);
//		RuntimeHandler run = new RuntimeHandler();
//		//run.execScript();
//		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/ShowTest?idTest="+id));
		CsvHandler csv = new CsvHandler("","");
		//csv.deletePortionCsv(1,10);
		//csv.echantillon(100);
		//csv.lissageOrdre2();
		//csv.switchColomn();
		//float result = csv.maxValueColumn(3);
		//System.out.println("RESULT = "+result);
		//csv.factorColumn(1, 100);
		csv.datToCsv("/home/max/BatMeca/data/X63_ST_L02.dat", "/home/max/output.csv");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
