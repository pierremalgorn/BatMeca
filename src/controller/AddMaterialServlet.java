package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MaterialService;
import service.manager.ServiceManager;
import entity.Material;

/**
 * Servlet implementation class addMaterialServlet
 */
@WebServlet("/addMaterial")
public class addMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MaterialService materialService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMaterialServlet() {
        super();
        materialService = ServiceManager.INSTANCE.getMaterialService();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addMaterial.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("inputName");
		System.out.println("NAME  = "+name);
		Material mat = new Material();
		mat.setName(name);
		
		materialService.addMaterial(mat);
		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/IndexMaterial"));
	}

}
