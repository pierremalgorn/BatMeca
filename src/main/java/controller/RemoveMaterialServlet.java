package controller;

import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MaterialService;
import controller.util.ServletInitParametersAware;
import entity.Material;

/**
 * Permet de supprimer un matériaux
 * Servlet implementation class REmoveMaterialServlet
 */
@Controller
@RequestMapping("/RemoveMaterial")
public class RemoveMaterialServlet extends ServletInitParametersAware {

	@Autowired
	private MaterialService materialService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idMat"));
		Material mat = materialService.find(id);
		FolderHandler f = new FolderHandler(getRessourcePath());
		f.cleanMatFolder(mat);
		materialService.remove(mat);
		RequestDispatcher rd = null;
		if (request.getParameter("idParent") != null) {
			rd = request.getRequestDispatcher("/Material?idMat="+request.getParameter("idParent"));
			
		} else {
			
			rd = request.getRequestDispatcher("/IndexMaterial");
		
		}

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
