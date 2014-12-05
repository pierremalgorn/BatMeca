package fr.epf.batmeca.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IMaterialService;
import fr.epf.batmeca.service.IUserService;

/**
 * Servlet implementation class IndexMaterial
 */
@Controller
@RequestMapping("/IndexMaterial")
public class IndexMaterialServlet {

	@Autowired
	private IMaterialService materialService;
	@Autowired
	private IUserService userService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model, Principal principal) {
		List<Material> list = null;

		User user = userService.getUser(principal.getName());
		if (user.getType().getId() == 1) {
			list = materialService.findAllNoParent();
		} else {
			list = materialService.findByUser(user);
		}

		model.addAttribute("materials", list);
		return "indexMaterial";
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
