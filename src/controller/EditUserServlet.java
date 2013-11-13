package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TypeUser;
import entity.User;
import service.UserService;
import service.TypeUserService;
import service.manager.ServiceManager;

@WebServlet("/EditUser")
public class EditUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private TypeUserService typeUserService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
		//création des Services
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

		request.setAttribute("user", userService.getUser(id));//récupération de l'ordinateur à modifier
		request.setAttribute("types", typeUserService.getTypes());//envoie de la liste des companies

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/editUser.jsp"));
		rd.forward(request, response);//envoie sur le formulaire de modification d'ordinateur
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String edit = request.getParameter("id");
		int id = Integer.parseInt(edit);
		String name = request.getParameter("name");
		String firtsname = request.getParameter("firstName");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		
		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));
		
		User user = userService.getUser(id);
		user.setName(name);
		user.setFirstName(firtsname);
		user.setEmail(email);
		user.setType(typeUser);
		String p = user.getPassword();
		user.setPassword(p);
		ServiceManager.INSTANCE.getUserService().editUser(user);//on modifie l'ordinateur dans la BDD

			// Redirection vers la page de la liste des utilisateur
			response.sendRedirect(response
					.encodeURL("/BatmecaNewGeneration/IndexUser"));
	}


}
