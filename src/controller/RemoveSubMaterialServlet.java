package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SubMaterial;
import service.SubMaterialService;
import service.manager.ServiceManager;

/**
 * Servlet implementation class RemoveSubMaterialServlet
 */
@WebServlet("/removeSubMaterial")
public class RemoveSubMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SubMaterialService subMaterialService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveSubMaterialServlet() {
        super();
        subMaterialService = ServiceManager.INSTANCE.getSubMaterialService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id  = Integer.parseInt((String) request.getParameter("id"));
		SubMaterial sub = subMaterialService.find(id);
		subMaterialService.remove(id);
		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/Material?idMaterial="+sub.getMaterial().getId()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
