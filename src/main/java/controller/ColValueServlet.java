package controller;

import handler.FolderHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.TestService;

import com.google.gson.Gson;

import controller.util.ServletInitParametersAware;
import entity.Test;

/**
 * Permet de créer un courbe en sélectionnant l'abscisse et l'ordonnée Servlet
 * implementation class ColValueServlet
 */
@Controller
@RequestMapping("/ColValue")
public class ColValueServlet extends ServletInitParametersAware {

	@Autowired
	private TestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int nbCol = Integer.parseInt(request.getParameter("nbField"));
		int id = Integer.parseInt(request.getParameter("inputId"));
		FolderHandler f = new FolderHandler(getRessourcePath());
		Test test = testService.find(id);
		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] elem = new String[nbCol];
		String[] unit = new String[nbCol];

		for (int i = 0; i < nbCol; i++) {
			elem[i] = request.getParameter("nameCol" + i);
			unit[i] = request.getParameter("unit" + i);
		}

		list.add(elem);
		list.add(unit);

		f.saveToJson(list, f.getPathSave(test) + File.separator + "header.json");
		response.getWriter().write(new Gson().toJson(list));
	}
}
