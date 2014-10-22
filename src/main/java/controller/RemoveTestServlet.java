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
import controller.util.ServletInitParametersAware;
import entity.Test;

/**
 * Servlet implementation class RemoveTestServlet
 */
@Controller
@RequestMapping("/RemoveTest")
public class RemoveTestServlet extends ServletInitParametersAware {

	@Autowired
    private TestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Test t = testService.find(Integer.parseInt(id));
		FolderHandler f = new FolderHandler(getRessourcePath());
		f.deleteFolder(t);
		testService.remove(t);
		
		response.sendRedirect(response.encodeURL("./Material?idMat="+request.getParameter("idMat")));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
