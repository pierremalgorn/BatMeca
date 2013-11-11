package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MaterialService;
import service.SubMaterialService;
import service.manager.ServiceManager;
import entity.Material;
import entity.SubMaterial;

/**
 * Servlet implementation class AddSubMaterial
 */
@WebServlet("/addSubMaterial")
public class AddSubMaterial extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SubMaterialService subMaterialService;
    private MaterialService materialService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubMaterial() {
        super();
       subMaterialService = ServiceManager.INSTANCE.getSubMaterialService();
       materialService = ServiceManager.INSTANCE.getMaterialService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idMat");
		request.setAttribute("id", id);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addSub.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("inputNameSub");
		String idMat = request.getParameter("idMat");
		System.out.println("NAME  = "+name);
		System.out.println("ID MAT = "+idMat);
		Material mat = new Material();
		
		mat = materialService.find(Integer.parseInt(idMat));
		
		SubMaterial sub = new SubMaterial();
		sub.setName(name);
		sub.setMaterial(mat);
		subMaterialService.add(sub);
		

		response.sendRedirect(response.encodeURL("/BatmecaNewGeneration/Material?idMaterial="+idMat));
	}

}
