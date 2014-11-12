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
@RequestMapping("/EditUser")
public class EditUserServlet {

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
		String edit = request.getParameter("id");
		int id = Integer.parseInt(edit);

		request.setAttribute("user", userService.getUser(id));
		request.setAttribute("types", typeUserService.getTypes());

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/editUser.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Récuêration des elements du formulaire
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String firtsname = request.getParameter("firstName");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newpassword");
		String newPasswordConfirm = request.getParameter("newpasswordconfirm");
		// Création de l'objet associer
		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));

		User user = userService.getUser(id);
		user.setName(name);
		user.setFirstName(firtsname);
		user.setEmail(email);
		user.setType(typeUser);
		// if user wants to change his password
		if (!password.equals("") && !newPassword.equals("")
				&& newPassword.equals(newPasswordConfirm)) {
			user.setPassword(newPassword);
		} else {
			user.setPassword(user.getPassword());
		}

		userService.editUser(user);

		// if(target.equals("profile")){
		response.sendRedirect(response.encodeURL("./User"));
		// } else {
		// response.sendRedirect(response.encodeURL("./IndexUser"));
		// }
	}
}
