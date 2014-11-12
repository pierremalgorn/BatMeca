package controller;

import handler.FolderHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.TestService;
import service.impl.ValueService;
import entity.Test;

/**
 * Servlet implementation class ShowResultServlet
 */
@Controller
@RequestMapping("/ShowResult")
public class ShowResultServlet {

	@Autowired
	private ValueService valueService;
	@Autowired
	private TestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Test test = testService.find(id);
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		String data = f.readResult(test);

		// request.setAttribute("results", data);
		response.getWriter().write(data);
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
