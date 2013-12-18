package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Création d'un fichier de configuration 
 * Non fonctionnelle
 * Servlet implementation class ConfigGeneratorServlet
 */
@WebServlet("/ConfigGenerator")
public class ConfigGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigGeneratorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/configGenerator.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String post = request.getParameter("POST");
		//System.out.println(post);
		Enumeration<String> params = request.getParameterNames();
		
		while (params.hasMoreElements()) {
			String string = (String) params.nextElement();
			//String val = request.getParameter(string);
			
			System.out.println("Name Field = "+string);
			//System.out.println("Val Filed = "+val);
		}
		this.doGet(request, response);
	}

}
