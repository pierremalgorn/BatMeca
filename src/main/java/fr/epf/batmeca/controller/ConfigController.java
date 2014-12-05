package fr.epf.batmeca.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.ITypeTestAttributService;

@Controller
public class ConfigController {

	@Autowired
	private ITypeMaterialAttributService typeMatAttrService;
	@Autowired
	private ITypeTestAttributService typeTestAttrService;

	@RequestMapping(value = "/Config", method = RequestMethod.GET)
	protected void configGet(HttpServletRequest request,
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

	@RequestMapping(value = "/Config", method = RequestMethod.POST)
	protected void configPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value = "/ConfigGenerator", method = RequestMethod.GET)
	protected void configGeneratorGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/configGenerator.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/ConfigGenerator", method = RequestMethod.POST)
	protected void configGeneratorPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String post = request.getParameter("POST");
		// System.out.println(post);
		Enumeration<String> params = request.getParameterNames();

		while (params.hasMoreElements()) {
			String string = params.nextElement();
			// String val = request.getParameter(string);

			System.out.println("Name Field = " + string);
			// System.out.println("Val Filed = "+val);
		}
		configGeneratorGet(request, response);
	}
}
