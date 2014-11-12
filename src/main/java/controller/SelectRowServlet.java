package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
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
 * Permet de selectioner un courbe Servlet implementation class SelectRowServlet
 */
@Controller
@RequestMapping("/SelectRow")
public class SelectRowServlet extends ServletInitParametersAware {

	@Autowired
	private TestService testService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* Récuperation de l'abscisse et de l'ordonnées */
		int x = Integer.parseInt(request.getParameter("inputX"));
		int y = Integer.parseInt(request.getParameter("inputY"));

		Test test = testService.find(Integer.parseInt(request
				.getParameter("inputId")));
		FolderHandler f = new FolderHandler(getRessourcePath());
		CsvHandler csv = new CsvHandler(getRoot() + File.separator + getName());
		File[] list = f.listCurve(test);
		int nbCurve = list.length;
		// Verification si le tracer n'a pas deja été éffectuer
		boolean content = false;
		for (File file : list) {
			if (file.getName().compareTo(x + "-" + y + ".csv") == 0) {
				content = true;
			}
		}
		if (content == false) {
			// Création de la courbe
			try {
				csv.selectCurve(f.getPathSave(test) + File.separator
						+ "dataInput.csv", f.getPathSave(test) + File.separator
						+ "curve" + File.separator + x + "-" + y + ".csv", x, y);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String data = csv.readAll(f.getPathSave(test) + File.separator
					+ "curve" + File.separator + x + "-" + y + ".csv");
			response.setContentType("application/json");
			response.getWriter().write(
					"{\"data\":" + data + ",\"nbCurve\":" + (nbCurve + 1)
							+ ",\"nameFile\":\"" + x + "-" + y + ".csv\"}");
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{\"content\":true}");
		}

		// response.getWriter().write((new Gson().toJson(nbCurve)));
	}
}
