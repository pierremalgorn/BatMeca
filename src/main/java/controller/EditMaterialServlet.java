package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Material;
import service.MaterialService;
import service.manager.ServiceManager;

/**
 * Servlet implementation class EditMaterialServlet
 */
@WebServlet("/EditMaterial")
public class EditMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MaterialService materialService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMaterialServlet() {
        super();
        materialService = ServiceManager.INSTANCE.getMaterialService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Material mat = materialService.find(id);
		request.setAttribute("mat", mat);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
		response.encodeURL("/WEB-INF/editMaterial.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Material mat = materialService.find(id);
		mat.setName(request.getParameter("inputName"));
		
		materialService.editMaterial(mat);
		response.sendRedirect(response
				.encodeURL("/BatmecaNewGeneration/IndexMaterial"));
	}

}
