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

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.service.MaterialService;
import fr.epf.batmeca.service.TestService;

/**
 * Servlet implementation class MaterialServlet
 */
@Controller
@RequestMapping("/Material")
public class MaterialServlet {

	@Autowired
	private MaterialService materialService;
	@Autowired
	private TestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Récupération de la liste des matériaux
		 */
		String id = request.getParameter("idMat");
		Material mat = null;
		List<Material> childs = null;

		mat = materialService.find(Integer.parseInt(id));
		childs = materialService.findByParent(mat);
		System.out.println("NB child = " + childs.size());
		request.setAttribute("material", mat);
		request.setAttribute("childs", childs);

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/material.jsp"));
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
