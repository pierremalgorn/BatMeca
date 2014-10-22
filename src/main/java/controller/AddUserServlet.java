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


@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	private UserService userService;
	private TypeUserService typeUserService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        userService = ServiceManager.INSTANCE.getUserService();
        typeUserService = ServiceManager.INSTANCE.getTypeUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("types", typeUserService.getTypes());//envoie de la liste des types
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addUser.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Création de l'objet associer
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
		
		//enregistrement en base de données
		userService.addUser(user);
		response.sendRedirect(response.encodeURL("./IndexUser"));
	}

}
