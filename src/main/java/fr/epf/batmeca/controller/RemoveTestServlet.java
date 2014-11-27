package fr.epf.batmeca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.service.ITestService;
import fr.epf.batmeca.service.impl.ValueServiceImpl;

/**
 * Servlet implementation class RemoveTestServlet
 */
@Controller
@RequestMapping("/RemoveTest")
public class RemoveTestServlet {

	@Autowired
	private ValueServiceImpl valueService;
	@Autowired
	private ITestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Test t = testService.find(Integer.parseInt(id));
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		f.deleteFolder(t);
		testService.remove(t);

		response.sendRedirect(response.encodeURL("./Material?idMat="
				+ request.getParameter("idMat")));
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
