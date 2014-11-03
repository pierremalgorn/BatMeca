package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MaterialService;
import entity.Material;
import entity.User;

/**
 * Servlet implementation class IndexMaterial
 */
@Controller
@RequestMapping("/IndexMaterial")
public class IndexMaterialServlet {

	@Autowired
	private MaterialService materialService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Material> list = null;

		/**
		 * Récuepration de la liste des materiau
		 * */
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		if (user.getType().getId() == 1) {
			list = materialService.findAllNoParent();
		} else {
			list = materialService.findByUser((User) session
					.getAttribute("sessionUser"));
		}

		request.setAttribute("materials", list);
		System.out.println("TAILLE LIST = " + list.size());
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/indexMaterial.jsp"));
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
