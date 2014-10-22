package controller;

import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MaterialService;
import service.manager.ServiceManager;
import entity.Material;

/**
 * Permet de supprimer un matériaux
 * Servlet implementation class REmoveMaterialServlet
 */
@WebServlet("/RemoveMaterial")
public class RemoveMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MaterialService materialService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveMaterialServlet() {
		super();
		materialService = ServiceManager.INSTANCE.getMaterialService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idMat"));
		Material mat = materialService.find(id);
		ServletContext context = getServletContext();
		FolderHandler f = new FolderHandler(context.getInitParameter("ressourcePath"));
		f.cleanMatFolder(mat);
		materialService.remove(mat);
		RequestDispatcher rd = null;
		if (request.getParameter("idParent") != null) {
			rd = getServletContext().getRequestDispatcher("/Material?idMat="+request.getParameter("idParent"));
			
		} else {
			
			rd = getServletContext().getRequestDispatcher("/IndexMaterial");
		
		}

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}