package controller;

import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TestService;
import service.manager.ServiceManager;
import entity.Test;

/**
 * Servlet implementation class RemoveTestServlet
 */
@WebServlet("/RemoveTest")
public class RemoveTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Test t = testService.find(Integer.parseInt(id));
		FolderHandler f = new FolderHandler();
		f.deleteFolder(t);
		testService.remove(Integer.parseInt(id));
		
		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/IndexTest?idSub="+request.getParameter("idSub")));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
