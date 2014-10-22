package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TestService;
import service.manager.ServiceManager;
import entity.Test;

/**
 * Servlet implementation class IndexTestServlet
 */
@WebServlet("/IndexTest")
public class IndexTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Récuperation de la liste des essais associés 
		 * */
	
		List<Test> tests;
		tests = testService.findAll();
		
		request.setAttribute("tests", tests);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/indexTest.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
