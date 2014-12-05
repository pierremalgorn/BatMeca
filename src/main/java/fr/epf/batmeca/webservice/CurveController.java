package fr.epf.batmeca.webservice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.handler.CsvHandler;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.service.ITestService;
import fr.epf.batmeca.service.impl.ValueServiceImpl;

@RestController
public class CurveController {

	@Autowired
	private ValueServiceImpl valueService;
	@Autowired
	private ITestService testService;

	/**
	 * Permet de selectioner une courbe
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/SelectRow", method = RequestMethod.POST)
	protected String doPost(@RequestParam("inputX") String inputX,
			@RequestParam("inputY") String inputY,
			@RequestParam("inputId") String inputId) throws IOException {

		int x = Integer.parseInt(inputX);
		int y = Integer.parseInt(inputY);

		Test test = testService.find(Integer.parseInt(inputId));
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		CsvHandler csv = new CsvHandler(valueService.getRoot() + File.separator
				+ valueService.getName());
		File[] list = f.listCurve(test);
		int nbCurve = list.length;

		boolean content = false;
		for (File file : list) {
			if (file.getName().compareTo(x + "-" + y + ".csv") == 0) {
				content = true;
			}
		}
		// response.setContentType("application/json"); TODO clean all that
		if (content) {
			return "{\"content\":true}";
		} else {
			// Création de la courbe
			csv.selectCurve(f.getPathSave(test) + File.separator
					+ "dataInput.csv", f.getPathSave(test) + File.separator
					+ "curve" + File.separator + x + "-" + y + ".csv", x, y);

			String data = csv.readAll(f.getPathSave(test) + File.separator
					+ "curve" + File.separator + x + "-" + y + ".csv");

			return "{\"data\":" + data + ",\"nbCurve\":" + (nbCurve + 1)
					+ ",\"nameFile\":\"" + x + "-" + y + ".csv\"}";
		}
	}

	/**
	 * Permet de créer une courbe en sélectionnant l'abscisse et l'ordonnée
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/ColValue", method = RequestMethod.POST)
	protected ArrayList<String[]> colValuePost(
			@RequestParam("nbField") String nbField,
			@RequestParam("inputId") String inputId, HttpServletRequest request)
			throws IOException {

		int nbCol = Integer.parseInt(nbField);
		int id = Integer.parseInt(inputId);
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

		// TODO clean all that
		f.saveToJson(list, f.getPathSave(test) + File.separator + "header.json");
		return list;
	}

	@RequestMapping(value = "/RemoveCurve", method = RequestMethod.GET)
	protected String doGet(@RequestParam("file") String file) {
		File curve = new File(file);
		boolean delete = curve.delete();
		return "{" + delete + "}";
	}
}
