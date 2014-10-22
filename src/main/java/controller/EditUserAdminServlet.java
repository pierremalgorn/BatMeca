package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TypeUserService;
import service.UserService;
import service.manager.ServiceManager;
import entity.TypeUser;
import entity.User;

@WebServlet("/EditUserAdmin")
public class EditUserAdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private TypeUserService typeUserService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserAdminServlet() {
		super();
		//cr�ation des Services
		userService = ServiceManager.INSTANCE.getUserService();
		typeUserService = ServiceManager.INSTANCE.getTypeUserService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String edit = request.getParameter("id");
		int id = Integer.parseInt(edit);

		request.setAttribute("user", userService.getUser(id));
		request.setAttribute("types", typeUserService.getTypes());

		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/editUserAdmin.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Récuêration des elements du formulaire
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String firtsname = request.getParameter("firstName");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newpassword");
		String newPasswordConfirm = request.getParameter("newpasswordconfirm");
		//Création de l'objet associer
		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));
		
		User user = userService.getUser(id);
		user.setName(name);
		user.setFirstName(firtsname);
		user.setEmail(email);
		user.setType(typeUser);
		//if user wants to change his password
		if(!password.equals("") 
				&& !newPassword.equals("") 
				&& newPassword.equals(newPasswordConfirm)){
			user.setPassword(newPassword);
		} else {
			user.setPassword(user.getPassword());
		}

		ServiceManager.INSTANCE.getUserService().editUser(user);

		response.sendRedirect(response.encodeURL("IndexUser"));

		//response.sendRedirect(response.encodeURL("IndexUser"));
	}


}
