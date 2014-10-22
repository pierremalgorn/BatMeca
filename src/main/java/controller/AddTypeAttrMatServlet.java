package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TypeMaterialAttributService;
import service.manager.ServiceManager;
import entity.TypeMaterialAttribute;

/**
 * Servlet implementation class AddTypeAttrMat
 */
@WebServlet("/AddTypeAttrMat")
public class AddTypeAttrMatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TypeMaterialAttributService typeMatAttrService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTypeAttrMatServlet() {
        super();
        typeMatAttrService = ServiceManager.INSTANCE.getTypeMaterialAttributService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addTypeAttrMat.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Création objet associé 
		 * */
		TypeMaterialAttribute type = new TypeMaterialAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));
		//enregistrement en base de données
		typeMatAttrService.add(type);
		
	
		response.sendRedirect(response.encodeURL("./Config"));
	}

}
