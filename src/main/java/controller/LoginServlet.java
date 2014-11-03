package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.UserService;
import entity.User;

/**
 * Permet de loger un utilisateur Servlet implementation class LoginController
 */
@Controller
@RequestMapping("/Login")
public class LoginServlet {

	@Autowired
	private UserService userService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session != null) {
			session.removeAttribute("sessionUser");
		}

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/login.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("password");

		User user = null;
		user = userService.getUserByLoginMdp(login, mdp);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionUser", user);
			response.sendRedirect(response.encodeURL("./IndexMaterial"));
		} else {
			request.setAttribute("error", "yes");
			this.doGet(request, response);
		}

	}

}
