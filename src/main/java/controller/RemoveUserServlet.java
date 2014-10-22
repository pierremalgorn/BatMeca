package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Test;
import entity.TypeUser;
import entity.User;
import service.UserService;
import service.TypeUserService;
import service.manager.ServiceManager;

/*
 * Permet de supprimer un utilisateur
 * */

@WebServlet("/RemoveUser")
public class RemoveUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private TypeUserService typeUserService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveUserServlet() {
		super();
		//cr?ation des Services
		userService = ServiceManager.INSTANCE.getUserService();
		typeUserService = ServiceManager.INSTANCE.getTypeUserService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.getUser(Integer.parseInt(id));
		userService.removeUser(user);
		RequestDispatcher rd = null;
        rd = getServletContext().getRequestDispatcher("/IndexUser");
        request.setAttribute("event", "userremoved");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


}
