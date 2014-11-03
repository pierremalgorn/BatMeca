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

import service.TypeMaterialAttributService;
import entity.TypeMaterialAttribute;

/**
 * Servlet implementation class AddTypeAttrMat
 */
@Controller
@RequestMapping("/AddTypeAttrMat")
public class AddTypeAttrMatServlet {

	@Autowired
	private TypeMaterialAttributService typeMatAttrService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addTypeAttrMat.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Création objet associé
		 */
		TypeMaterialAttribute type = new TypeMaterialAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));
		// enregistrement en base de données
		typeMatAttrService.add(type);
		response.sendRedirect(response.encodeURL("./Config"));
	}
}
