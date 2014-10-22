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
 * Permet de supprimer un type d'attribut matériaux
 * Servlet implementation class RemoveTypeMatAttrServlet
 * 
 */
@Controller
@RequestMapping("/RemoveTypeMatAttr")
public class RemoveTypeMatAttrServlet {

	@Autowired
    private TypeMaterialAttributService typeService;  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TypeMaterialAttribute type;
		type = typeService.find(Integer.parseInt(request.getParameter("id")));
		typeService.remove(type);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/Config");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
