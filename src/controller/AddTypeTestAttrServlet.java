package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TypeMaterialAttribute;
import entity.TypeTestAttribute;
import service.TypeMaterialAttributService;
import service.TypeTestAttributService;
import service.manager.ServiceManager;

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
		TypeTestAttribute type = new TypeTestAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));
		typeService.add(type);
		
		/*RequestDispatcher rd = null;
		rd = getServletContext().getRequestDispatcher("/Config");
		rd.forward(request, response);*/
		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/Config"));
	}

}
