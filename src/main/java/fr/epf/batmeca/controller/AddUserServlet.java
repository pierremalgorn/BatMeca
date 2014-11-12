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

import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.TypeUserService;
import fr.epf.batmeca.service.UserService;

@Controller
@RequestMapping("/addUser")
public class AddUserServlet {

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
		// Envoi de la liste des types
		request.setAttribute("types", typeUserService.getTypes());
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addUser.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Création de l'objet associer
		String name = request.getParameter("name");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int idType;
		String type = request.getParameter("type");
		idType = Integer.parseInt(type);
		TypeUser typeUser = new TypeUser();
		typeUser.setId(idType);

		User user = new User();
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);
		user.setPassword(password);
		user.setType(typeUser);

		// enregistrement en base de données
		userService.addUser(user);
		response.sendRedirect(response.encodeURL("./IndexUser"));
	}
}
