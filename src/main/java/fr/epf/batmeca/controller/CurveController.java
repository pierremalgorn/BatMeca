package fr.epf.batmeca.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.handler.CsvHandler;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.service.ITestService;
import fr.epf.batmeca.service.impl.ValueServiceImpl;

@Controller
public class CurveController {

	@Autowired
	private ValueServiceImpl valueService;
	@Autowired
	private ITestService testService;

	/**
	 * Permet de selectioner une courbe
	 */
	@RequestMapping(value = "/SelectRow", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* Récuperation de l'abscisse et de l'ordonnées */
		int x = Integer.parseInt(request.getParameter("inputX"));
		int y = Integer.parseInt(request.getParameter("inputY"));

		Test test = testService.find(Integer.parseInt(request
				.getParameter("inputId")));
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		CsvHandler csv = new CsvHandler(valueService.getRoot() + File.separator
				+ valueService.getName());
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

	/**
	 * Permet de créer une courbe en sélectionnant l'abscisse et l'ordonnée
	 */
	@RequestMapping(value = "/ColValue", method = RequestMethod.POST)
	protected void colValuePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int nbCol = Integer.parseInt(request.getParameter("nbField"));
		int id = Integer.parseInt(request.getParameter("inputId"));
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
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

	@RequestMapping(value = "/RemoveCurve", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String file = request.getParameter("file");
		File curve = new File(file);
		boolean delete = curve.delete();
		response.getWriter().write("{" + delete + "}");
	}
}
