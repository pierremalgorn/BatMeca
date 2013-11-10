package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Material;
import entity.SubMaterial;
import service.MaterialService;
import service.SubMaterialService;
import service.manager.ServiceManager;

/**
 * Servlet implementation class MaterialServlet
 */
@WebServlet("/Material")
public class MaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MaterialService materialService;
	 SubMaterialService subMaterialService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaterialServlet() {
        super();
        materialService = ServiceManager.INSTANCE.getMaterialService();
        subMaterialService = ServiceManager.INSTANCE.getSubMaterialService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idMaterial");
		Material mat = null;
		mat = materialService.find(Integer.parseInt(id));
		List<SubMaterial> subs = null;
		System.out.println("trace 1");
		subs = subMaterialService.findByMat(mat);
		System.out.println("trace 2");
		request.setAttribute("material", mat);
		request.setAttribute("subs", subs);
		System.out.println("NB SUB = "+subs.size());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/material.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
