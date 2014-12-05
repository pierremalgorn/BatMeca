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
import fr.epf.batmeca.service.IFileService;
import fr.epf.batmeca.service.ITestService;

@RestController
public class CurveController {

	@Autowired
	private ITestService testService;
	@Autowired
	private IFileService fileService;

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
		CsvHandler csv = new CsvHandler();
		File[] list = fileService.listCurve(test);
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
			csv.selectCurve(fileService.getTestPath(test) + File.separator
					+ "dataInput.csv", fileService.getTestPath(test) + File.separator
					+ "curve" + File.separator + x + "-" + y + ".csv", x, y);

			String data = csv.readAll(fileService.getTestPath(test) + File.separator
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
		fileService.saveToJson(list, fileService.getTestPath(test) + File.separator + "header.json");
		return list;
	}

	@RequestMapping(value = "/RemoveCurve", method = RequestMethod.GET)
	protected String doGet(@RequestParam("file") String file) {
		File curve = new File(file);
		boolean delete = curve.delete();
		return "{" + delete + "}";
	}
}
