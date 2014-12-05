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

import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.service.ITypeMaterialAttributService;

@Controller
public class TypeMatAttrController {

	@Autowired
	private ITypeMaterialAttributService typeService;

	@RequestMapping(value = "/AddTypeAttrMat", method = RequestMethod.GET)
	protected void addTypeAttrMatGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addTypeAttrMat.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/AddTypeAttrMat", method = RequestMethod.POST)
	protected void addTypeAttrMatPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TypeMaterialAttribute type = new TypeMaterialAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));

		typeService.add(type);
		response.sendRedirect(response.encodeURL("./Config"));
	}

	@RequestMapping(value = "/RemoveTypeMatAttr", method = RequestMethod.GET)
	protected void removeTypeMatAttrGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TypeMaterialAttribute type;
		type = typeService.find(Integer.parseInt(request.getParameter("id")));
		typeService.remove(type);

		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/Config");
		rd.forward(request, response);
	}

	@RequestMapping(value = "/RemoveTypeMatAttr", method = RequestMethod.POST)
	protected void removeTypeMatAttrPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
