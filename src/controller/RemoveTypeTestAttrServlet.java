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
import entity.TypeMaterialAttribute;
import entity.TypeTestAttribute;

/**
 * Servlet implementation class RemoveTypeTestAttrServlet
 */
@WebServlet("/RemoveTypeTestAttr")
public class RemoveTypeTestAttrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeTestAttributService typeService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTypeTestAttrServlet() {
        super();
        typeService = ServiceManager.INSTANCE.getTypeTestAttributService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TypeTestAttribute type;
		type = typeService.find(Integer.parseInt(request.getParameter("id")));
		typeService.remove(type);
		
		RequestDispatcher rd = null;
		rd = getServletContext().getRequestDispatcher("/Config");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
