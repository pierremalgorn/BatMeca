package fr.epf.batmeca.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.ITypeTestAttributService;

@Controller
public class TypeTestAttrController {

	@Autowired
	private ITypeTestAttributService typeService;

	@RequestMapping(value = "/AddTypeTestAttr", method = RequestMethod.GET)
	protected void addTypeTestAttrGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addTypeTestAttr.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/AddTypeTestAttr", method = RequestMethod.POST)
	protected void addTypeTestAttrPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// créattion objet associé
		TypeTestAttribute type = new TypeTestAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));
		// enregistrement en base de données
		typeService.add(type);
		response.sendRedirect(response.encodeURL("./Config"));
	}

	@RequestMapping(value = "/RemoveTypeTestAttr", method = RequestMethod.GET)
	protected void removeTypeTestAttrGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TypeTestAttribute type;
		type = typeService.find(Integer.parseInt(request.getParameter("id")));
		typeService.remove(type);

		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/Config");
		rd.forward(request, response);
	}

	@RequestMapping(value = "/RemoveTypeTestAttr", method = RequestMethod.POST)
	protected void removeTypeTestAttrPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
