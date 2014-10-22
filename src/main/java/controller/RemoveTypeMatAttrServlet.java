package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TypeMaterialAttribute;
import service.TypeMaterialAttributService;
import service.manager.ServiceManager;

/**
 * Permet de supprimer un type d'attribut matériaux
 * Servlet implementation class RemoveTypeMatAttrServlet
 * 
 */
@WebServlet("/RemoveTypeMatAttr")
public class RemoveTypeMatAttrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TypeMaterialAttributService typeService;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTypeMatAttrServlet() {
        super();
        typeService = ServiceManager.INSTANCE.getTypeMaterialAttributService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TypeMaterialAttribute type;
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
