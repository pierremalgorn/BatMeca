package fr.epf.batmeca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
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

@Controller
public class ShowController {

	@Autowired
	private ValueServiceImpl valueService;
	@Autowired
	private ITestService testService;

	@RequestMapping(value = "/ShowHistoric", method = RequestMethod.GET)
	protected void showHistoricGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Test test = testService.find(id);
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		String data = f.readHistoric(test);

		// request.setAttribute("results", data);
		response.getWriter().write(data);
	}

	@RequestMapping(value = "/ShowHistoric", method = RequestMethod.POST)
	protected void showHistoricPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value = "/ShowResult", method = RequestMethod.GET)
	protected void showResultGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Test test = testService.find(id);
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		String data = f.readResult(test);

		// request.setAttribute("results", data);
		response.getWriter().write(data);
	}

	@RequestMapping(value = "/ShowResult", method = RequestMethod.POST)
	protected void showResultPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
