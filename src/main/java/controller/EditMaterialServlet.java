package controller;

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
import entity.Material;

/**
 * Servlet implementation class EditMaterialServlet
 */
@Controller
@RequestMapping("/EditMaterial")
public class EditMaterialServlet {

	@Autowired
	private MaterialService materialService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Material mat = materialService.find(id);
		request.setAttribute("mat", mat);
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/editMaterial.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Material mat = materialService.find(id);
		mat.setName(request.getParameter("inputName"));

		materialService.editMaterial(mat);
		response.sendRedirect(response.encodeURL("./IndexMaterial"));
	}
}
