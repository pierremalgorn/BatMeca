package fr.epf.batmeca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.TypeMaterialAttributService;
import fr.epf.batmeca.service.TypeTestAttributService;

/**
 * Servlet implementation class ConfigServlet
 */
@Controller
@RequestMapping("/Config")
public class ConfigServlet {

	@Autowired
	private TypeMaterialAttributService typeMatAttrService;
	@Autowired
	private TypeTestAttributService typeTestAttrService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Recuperation des listes d'attributs
		 */
		List<TypeMaterialAttribute> typesMat = typeMatAttrService.findAll();
		List<TypeTestAttribute> typesTest = typeTestAttrService.findAll();

		request.setAttribute("typeMats", typesMat);
		request.setAttribute("typeTests", typesTest);

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/config.jsp"));
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
