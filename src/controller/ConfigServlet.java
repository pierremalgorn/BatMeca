package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TypeMaterialAttributService;
import service.TypeTestAttributService;
import service.manager.ServiceManager;
import entity.TypeMaterialAttribute;
import entity.TypeTestAttribute;

/**
 * Servlet implementation class ConfigServlet
 */
@WebServlet("/Config")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeMaterialAttributService typeMatAttrService;  
	private TypeTestAttributService typeTestAttrService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet() {
        super();
        typeMatAttrService = ServiceManager.INSTANCE.getTypeMaterialAttributService();
        typeTestAttrService = ServiceManager.INSTANCE.getTypeTestAttributService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<TypeMaterialAttribute> typesMat = typeMatAttrService.findAll();
		List<TypeTestAttribute> typesTest = typeTestAttrService.findAll();
		
		request.setAttribute("typeMats", typesMat);
		request.setAttribute("typeTests", typesTest);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/config.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
