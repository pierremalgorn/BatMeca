package controller;

import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Test;
import service.TestService;
import service.manager.ServiceManager;

/**
 * Servlet implementation class ShowHistoricServlet
 */
@WebServlet("/ShowHistoric")
public class ShowHistoricServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHistoricServlet() {
        super();
       testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Test test = testService.find(id);
		ServletContext context = getServletContext();
		FolderHandler f = new FolderHandler(context.getInitParameter("ressourcePath"));
		String data = f.readHistoric(test);
		
		//request.setAttribute("results", data);
		response.getWriter().write(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
