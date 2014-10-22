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

import service.TypeTestAttributService;
import entity.TypeTestAttribute;

/**
 * Servlet implementation class AddTypeTestAttrServlet
 */
@Controller
@RequestMapping("/AddTypeTestAttr")
public class AddTypeTestAttrServlet {

	@Autowired
    private TypeTestAttributService typeService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(
				response.encodeURL("/WEB-INF/addTypeTestAttr.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//créattion objet associé
		TypeTestAttribute type = new TypeTestAttribute();
		type.setName(request.getParameter("inputName"));
		type.setPattern(request.getParameter("inputPattern"));
		//enregistrement en base de données
		typeService.add(type);
		
		
		response.sendRedirect(response.encodeURL("./Config"));
	}

}
