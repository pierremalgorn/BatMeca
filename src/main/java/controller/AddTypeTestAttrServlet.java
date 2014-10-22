package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TypeTestAttributService;
import service.manager.ServiceManager;
import entity.TypeTestAttribute;

/**
 * Servlet implementation class AddTypeTestAttrServlet
 */
@WebServlet("/AddTypeTestAttr")
public class AddTypeTestAttrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TypeTestAttributService typeService;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTypeTestAttrServlet() {
        super();
       typeService = ServiceManager.INSTANCE.getTypeTestAttributService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addTypeTestAttr.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//créattion objet associé
		TypeTestAttribute type = new TypeTestAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));
		//enregistrement en base de données
		typeService.add(type);
		
		
		response.sendRedirect(response.encodeURL("./Config"));
	}

}