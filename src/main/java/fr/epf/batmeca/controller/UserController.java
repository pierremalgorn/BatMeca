package fr.epf.batmeca.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.ITypeUserService;
import fr.epf.batmeca.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITypeUserService typeUserService;

	@RequestMapping(value="/User", method = RequestMethod.GET)
	protected String userGet(ModelMap model, Principal principal) {
		User user = userService.getUser(principal.getName());
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping(value="/User", method = RequestMethod.POST)
	protected void userPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value="/IndexUser", method = RequestMethod.GET)
	protected void indexUserGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Récuperation de la liste des utilisateurs
		 * */
		List<User> list = null;
		list = userService.findAllUsers();
		request.setAttribute("users", list);
		System.out.println("TAILLE LIST = " + list.size());
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/indexUser.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value="/IndexUser", method = RequestMethod.POST)
	protected void indexUserPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value="/addUser", method = RequestMethod.GET)
	protected void addUserGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoi de la liste des types
		request.setAttribute("types", typeUserService.getTypes());
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addUser.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	protected void addUserPost(HttpServletRequest request,
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

	@RequestMapping(value="/EditUser", method = RequestMethod.GET)
	protected void editUserGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String edit = request.getParameter("id");
		int id = Integer.parseInt(edit);

		request.setAttribute("user", userService.getUser(id));
		request.setAttribute("types", typeUserService.getTypes());

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/editUser.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value="/EditUser", method = RequestMethod.POST)
	protected void editUserPost(HttpServletRequest request,
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

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/EditUserAdmin", method = RequestMethod.GET)
	protected void editUserAdminGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String edit = request.getParameter("id");
		int id = Integer.parseInt(edit);

		request.setAttribute("user", userService.getUser(id));
		request.setAttribute("types", typeUserService.getTypes());

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/editUserAdmin.jsp"));
		rd.forward(request, response);
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/EditUserAdmin", method = RequestMethod.POST)
	protected void editUserAdminPost(HttpServletRequest request,
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
		response.sendRedirect(response.encodeURL("IndexUser"));
		// response.sendRedirect(response.encodeURL("IndexUser"));
	}

	@RequestMapping(value="/RemoveUser", method = RequestMethod.GET)
	protected void removeUserGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.getUser(Integer.parseInt(id));
		userService.removeUser(user);
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/IndexUser");
		request.setAttribute("event", "userremoved");
		rd.forward(request, response);
	}

	@RequestMapping(value="/RemoveUser", method = RequestMethod.POST)
	protected void removeUserPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
