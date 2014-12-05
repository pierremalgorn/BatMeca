package fr.epf.batmeca.webservice;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.handler.CsvHandler;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.service.ITestService;

/**
 * Permet de réaliser des traitements sur une courbe
 */
@RestController
public class TraitmentController {

	@Autowired
	private ITestService testService;

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

		int idTest = Integer.parseInt(id);
		Test t = testService.find(idTest);
		CsvHandler csv = new CsvHandler();
		StringBuilder result = new StringBuilder();

		FolderHandler f = new FolderHandler();
		// Réalisation du lissage
		if (lisser != null) {
			// FIXME check file not null

			f.addDataHistoryFile("Lisser Data;file " + file, t);
			csv.lissageOrdre2(file, f.getPathSave(t) + File.separator + "curve"
					+ File.separator + "outputLissageTmp.csv");
			// csv.lissageOrdre2(f.getPathSave(t)+File.separator+"dataInput.csv",
			// f.getPathSave(t)+File.separator+"dataOutput.csv");
			String data = csv.readAll(f.getPathSave(t) + File.separator
					+ "curve" + File.separator + "outputLissageTmp.csv");
			f.renameFile(f.getPathSave(t) + File.separator + "curve"
					+ File.separator + "outputLissageTmp.csv", file);

			result.append(data);
		}

		// Réalisation d'une coupe de courbe
		if (cut != null) {
			// FIXME check start, end, file not null
			if (after != null) {
				int start = Integer.parseInt(startValue);
				try {
					csv.cutAfter(start, file);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				f.addDataHistoryFile("Cut After:" + start + ";file " + file, t);
			} else if (before != null) {
				int end = Integer.parseInt(endValue);
				try {
					csv.cutBefore(end, file);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				f.addDataHistoryFile("Cut Before:" + end + ";file " + file
						+ ";", t);
			}

			String data = csv.readAll(file);
			result.append(data);
		}

		// Calcule du max d'une colonne
		if (calMax != null) {
			// FIXME check file not null
			float max = 0;
			try {
				max = csv.maxValueColumn(Integer.parseInt(calMax), file);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			f.addResult("MAX FILE:" + file + ";max=" + max + ";", t);
			f.addDataHistoryFile(
					"MAX FILE:" + file + ";calMax:" + calMax + ";", t);

			result.append(new Gson().toJson(max));
		}

		// Multiplication par un facteur
		if (factor != null) {

			f.addDataHistoryFile("factor FILE", t);
		}

		// Reset de la courbes
		if (reset != null) {
			// csv.datToCsv(f.getPathSave(t)+File.separator+"data"+File.separator+f.getFileNameData(t),
			// f.getPathSave(t)+File.separator+"dataInput.csv");
			String data = csv.readAll(f.getPathSave(t) + File.separator
					+ "dataInput.csv");
			System.out.println("RESET = " + reset);
			f.addDataHistoryFile("RESET FILE", t);
			result.append(data);
		}

		if (coef != null) {
			// FIXME check file not null
			f.addDataHistoryFile("CALCUL COEFF:FILE " + file, t);
			f.addResult("COEF=" + coef + ";File=" + file + ";", t);
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
		CsvHandler csv = new CsvHandler();

		FolderHandler f = new FolderHandler();
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

		csv.factorColumn(nbColumn, other, factor, file, f.getPathSave(test)
				+ File.separator + "curve" + File.separator + "factorcurve.csv");

		f.addDataHistoryFile("FACTOR:" + factor + ";FILE:" + file
				+ ";nbColumn:" + nbColumn, test);
		String data = csv.readAll(f.getPathSave(test) + File.separator
				+ "curve" + File.separator + "factorcurve.csv");
		f.renameFile(f.getPathSave(test) + File.separator + "curve"
				+ File.separator + "factorcurve.csv", file);

		return data;
	}
}
