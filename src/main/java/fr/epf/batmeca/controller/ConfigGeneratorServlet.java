package fr.epf.batmeca.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Création d'un fichier de configuration Non fonctionnelle Servlet
 * implementation class ConfigGeneratorServlet
 */
@Controller
@RequestMapping("/ConfigGenerator")
public class ConfigGeneratorServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/configGenerator.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String post = request.getParameter("POST");
		// System.out.println(post);
		Enumeration<String> params = request.getParameterNames();

		while (params.hasMoreElements()) {
			String string = params.nextElement();
			// String val = request.getParameter(string);

			System.out.println("Name Field = " + string);
			// System.out.println("Val Filed = "+val);
		}
		this.doGet(request, response);
	}
}
