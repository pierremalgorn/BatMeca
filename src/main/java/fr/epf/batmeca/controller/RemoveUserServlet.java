package fr.epf.batmeca.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.TypeUserService;
import fr.epf.batmeca.service.UserService;

/*
 * Permet de supprimer un utilisateur
 * */

@Controller
@RequestMapping("/RemoveUser")
public class RemoveUserServlet {

	@Autowired
	private UserService userService;
	@Autowired
	private TypeUserService typeUserService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.getUser(Integer.parseInt(id));
		userService.removeUser(user);
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/IndexUser");
		request.setAttribute("event", "userremoved");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
