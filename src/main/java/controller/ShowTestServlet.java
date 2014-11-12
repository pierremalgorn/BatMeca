package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Permet d'afficher un éssai Servlet implementation class ShowTestServlet
 */
@Controller
@RequestMapping("/ShowTest")
public class ShowTestServlet extends ServletInitParametersAware {

	@Autowired
	private TestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int idTest = Integer.parseInt(request.getParameter("idTest"));
		Test t = testService.find(idTest);
		CsvHandler csv = new CsvHandler(getRoot() + File.separator + getName());

		FolderHandler f = new FolderHandler(getRessourcePath());

		// Récuperation de l'header
		ArrayList<String[]> list = f.deserializeFileJson(f.getPathSave(t)
				+ File.separator + "header.json");

		// String data =
		// csv.readAll(f.getPathSave(t)+File.separator+"dataInput.csv");

		// Recuperation des données et du nom des fichiers
		File[] files = f.listCurve(t);
		ArrayList<String[]> listData = new ArrayList<String[]>();
		ArrayList<String> listFile = new ArrayList<String>();
		for (File file : files) {

			System.out.println("NAME = " + file.getAbsolutePath());
			listFile.add(file.getAbsolutePath());
			String[] tab = { csv.readAll(file.getAbsolutePath()),
					file.getAbsolutePath() };
			listData.add(tab);
		}

		request.setAttribute("colHeader", list);

		request.setAttribute("files", files);
		// request.setAttribute("data", data);
		request.setAttribute("listData", listData);
		request.setAttribute("test", t);
		request.setAttribute("listFile", new Gson().toJson(listFile));
		// request.setAttribute("listCol", listCol);

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/test.jsp"));
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
