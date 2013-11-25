package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MaterialService;
import service.TypeMaterialAttributService;
import service.manager.ServiceManager;
import entity.Material;
import entity.MaterialAttribute;
import entity.TypeMaterialAttribute;

/**
 * Servlet implementation class AddSubMaterialServlet
 */
@WebServlet("/AddSubMaterial")
public class AddSubMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MaterialService materialService;
	private TypeMaterialAttributService typeMaterialAttributService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubMaterialServlet() {
		super();
		materialService = ServiceManager.INSTANCE.getMaterialService();
		typeMaterialAttributService = ServiceManager.INSTANCE.getTypeMaterialAttributService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Material> materials;
		materials = materialService.findAll();
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();
		
		request.setAttribute("mats", materials);
		request.setAttribute("matAttrs", listAttr);
		
		
		
		request.setAttribute("idParent", request.getParameter("idParent"));
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addSubMaterial.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("inputName");
		System.out.println("NAME  = " + name);
		Material mat = new Material();
		mat.setName(name);
		String parent = request.getParameter("inputMaterialParent");

		Material matParent = materialService.find(Integer.parseInt(parent));
		mat.setMaterialParent(matParent);
		
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();
		mat.setMatAttrs(new HashSet<MaterialAttribute>());
		
		
		for (TypeMaterialAttribute tMatAttr : listAttr) {
			MaterialAttribute matAttr = new MaterialAttribute();
			String nameAttr = request.getParameter("input"+tMatAttr.getName());
			System.out.println("Name = "+nameAttr);
			matAttr.setValue(nameAttr);
			matAttr.setTypeMatAttr(tMatAttr);
			matAttr.setMaterial(mat);
			mat.addMaterialAttribute(matAttr);
		}
		materialService.addMaterial(mat);
		response.sendRedirect(response
				.encodeURL("/BatmecaNewGeneration/Material?idMat="+mat.getId()));
		
	}

}
