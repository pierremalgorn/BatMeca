package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.manager.ServiceManager;
import entity.User;

/**
 * Servlet implementation class IndexUser
 */
@WebServlet("/IndexUser")
public class IndexUserServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
	private UserService userService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexUserServlet() {
        super();
        userService = ServiceManager.INSTANCE.getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = null;
		list =  userService.findAllUsers();
		 request.setAttribute("users",list);
		 System.out.println("TAILLE LIST = "+list.size());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/indexUser.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
