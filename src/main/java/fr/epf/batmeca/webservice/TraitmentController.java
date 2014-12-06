package fr.epf.batmeca.webservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.handler.CsvHandler;
import fr.epf.batmeca.service.IFileService;
import fr.epf.batmeca.service.ITestService;

/**
 * Permet de réaliser des traitements sur une courbe
 */
@RestController
public class TraitmentController {

	@Autowired
	private ITestService testService;
	@Autowired
	private IFileService fileService;

	@RequestMapping(value = "/Traitment", method = RequestMethod.GET)
	protected String traitmentGet(@RequestParam(value = "id") String id,
			@RequestParam(value = "lisser", required = false) String lisser,
			@RequestParam(value = "cut", required = false) String cut,
			@RequestParam(value = "after", required = false) String after,
			@RequestParam(value = "before", required = false) String before,
			@RequestParam(value = "start", required = false) String startValue,
			@RequestParam(value = "end", required = false) String endValue,
			@RequestParam(value = "calMax", required = false) String calMax,
			@RequestParam(value = "factor", required = false) String factor,
			@RequestParam(value = "reset", required = false) String reset,
			@RequestParam(value = "coef", required = false) String coef,
			@RequestParam(value = "file", required = false) String file) throws IOException {

		/*
		 * TODO clean this function
		 *
		 * Shouldn't file be FileService.DATACSV_F?
		 */

		int idTest = Integer.parseInt(id);
		Test t = testService.find(idTest);
		StringBuilder result = new StringBuilder();

		// Réalisation du lissage
		if (lisser != null) {
			// FIXME check file not null

			fileService.addHistory("Lisser Data;file " + file, t);

			String data = fileService.lissageOrdre2(file, t);
			result.append(data);
		}

		// Réalisation d'une coupe de courbe
		if (cut != null) {
			// FIXME check start, end, file not null
			if (after != null) {
				int start = Integer.parseInt(startValue);
				try {
					CsvHandler.cutAfter(start, file);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fileService.addHistory("Cut After:" + start + ";file " + file, t);
			} else if (before != null) {
				int end = Integer.parseInt(endValue);
				try {
					CsvHandler.cutBefore(end, file);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fileService.addHistory("Cut Before:" + end + ";file " + file
						+ ";", t);
			}

			String data = CsvHandler.readAll(file);
			result.append(data);
		}

		// Calcule du max d'une colonne
		if (calMax != null) {
			// FIXME check file not null
			float max = 0;
			try {
				max = CsvHandler.maxValueColumn(Integer.parseInt(calMax), file);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			fileService.addResult("MAX FILE:" + file + ";max=" + max + ";", t);
			fileService.addHistory(
					"MAX FILE:" + file + ";calMax:" + calMax + ";", t);

			result.append(new Gson().toJson(max));
		}

		// Multiplication par un facteur
		if (factor != null) {
			fileService.addHistory("factor FILE", t);
		}

		// Reset de la courbes
		if (reset != null) {
			System.out.println("RESET = " + reset);
			fileService.addHistory("RESET FILE", t);
			String data = fileService.resetCurve(t);
			result.append(data);
		}

		if (coef != null) {
			// FIXME check file not null
			fileService.addHistory("CALCUL COEFF:FILE " + file, t);
			fileService.addResult("COEF=" + coef + ";File=" + file + ";", t);
		}

		return result.toString();
	}

	@RequestMapping(value = "/Traitment", method = RequestMethod.POST)
	protected String traitmentPost(
			@RequestParam(value = "inputId") String inputIdValue,
			@RequestParam(value = "inputFactor") String inputFactorValue,
			@RequestParam(value = "selectRow") String selectRowValue,
			@RequestParam(value = "file") String file) throws IOException {

		int idTest = Integer.parseInt(inputIdValue);
		float factor = Float.parseFloat(inputFactorValue);
		int nbColumn = Integer.parseInt(selectRowValue);

		Test test = testService.find(idTest);
		String[] tab = file.split("\\.");

		System.out.println("size  :" + tab.length);
		tab = tab[0].split("/");
		tab = tab[tab.length - 1].split("-");
		int other = 0;
		if (nbColumn == Integer.parseInt(tab[0])) {
			nbColumn = 1;
			other = 2;
		} else {
			nbColumn = 2;
			other = 1;
		}

		fileService.addHistory("FACTOR:" + factor + ";FILE:" + file
				+ ";nbColumn:" + nbColumn, test);
		String data = fileService.factorColumn(nbColumn, other, factor, file, test);

		return data;
	}
}
