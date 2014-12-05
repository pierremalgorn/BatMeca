package fr.epf.batmeca.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.google.gson.Gson;

public class CsvHandler {

	private String root;

	public CsvHandler(String input) {
		root = input;
	}

	/**
	 * Permet de renvoyé une chaine de caractere contenant l'integralité d'un
	 * fichier csv
	 *
	 * @param input
	 *            fichier a lire
	 * */
	public String readAll(String input) throws IOException {
		// CSVReader reader = new CSVReader(new FileReader(input),',','"',2);
		CSVReader reader = new CSVReader(new FileReader(input), ',',
				CSVWriter.NO_QUOTE_CHARACTER);
		StringBuilder sb = new StringBuilder();
		List<String[]> myEntries = reader.readAll();
		for (String[] strings : myEntries) {
			for (String string : strings) {
				sb.append(string + ",");
			}
			sb.append("\n");
		}
		reader.close();
		// System.out.println(sb.toString());
		return new Gson().toJson(sb.toString());
	}

	/**
	 * Permet l'echantillonage du fichier csv
	 * */
	public void echantillon(int echant) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(
				"/home/max/BatMeca/data.csv"), ',', /* FIXME hardcoded link */
		CSVWriter.NO_QUOTE_CHARACTER, 2);
		CSVWriter writer = new CSVWriter(new FileWriter(
				"/home/max/BatMeca/data1.csv"), ','); /* FIXME hardcoded link */
		List<String[]> myEntries = reader.readAll();
		for (int i = 0; i < myEntries.size(); i = i + echant) {
			// System.out.println("i = "+i);
			writer.writeNext(myEntries.get(i));
		}
		reader.close();
		writer.close();
	}

	/**
	 * Permet de faire la moyenne entre deux points
	 * */
	public float average(float f1, float f2) {
		return (f1 + f2) / 2.0f;
	}

	/**
	 * Lissage des points
	 *
	 * @param input
	 *            chemin du fichier d'entree
	 * @param output
	 *            chemin du fichier de sortie
	 * */
	public void lissageOrdre2(String input, String output) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(input), ',',
				CSVWriter.NO_QUOTE_CHARACTER, Character.MIN_VALUE);
		CSVWriter writer = new CSVWriter(new FileWriter(output), ',',
				CSVWriter.NO_QUOTE_CHARACTER, Character.MIN_VALUE);
		List<String[]> myEntries = reader.readAll();

		String[] ligne0 = myEntries.get(0);
		String[] ligne1 = myEntries.get(1);
		writer.writeNext(ligne0);
		writer.writeNext(ligne1);
		for (int i = 2; i < myEntries.size() - 1; i++) {
			String[] tab = myEntries.get(i);
			for (int j = 0; j < tab.length; j++) {
				float x1 = Float.parseFloat(myEntries.get(i - 1)[j]);
				float x2 = Float.parseFloat(myEntries.get(i + 1)[j]);
				tab[j] = Float.toString(this.average(x1, x2));
			}
			writer.writeNext(tab);
		}
		writer.writeNext(myEntries.get(myEntries.size() - 1));
		reader.close();
		writer.close();

	}

	/**
	 * Permet de supprimer un interval de point
	 *
	 * @throws InterruptedException
	 * */
	public void deletePortionCsv(String input, int start, int end)
			throws IOException, InterruptedException {
		/* FIXME hardcoded link */
		String[] cmd = new String[] { "/bin/sed", "-i", "-e",
				start + "," + end + "d", input };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
		process.waitFor();
	}

	/**
	 * Permet de calculer le max d'un colonne
	 *
	 * @throws InterruptedException
	 * */
	public Float maxValueColumn(int numColumn, String input)
			throws NumberFormatException, IOException, InterruptedException {
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $" + numColumn + "}",
				input };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";

		reader.readLine();
		Float max = (float) -999999999;
		while ((line = reader.readLine()) != null) {
			System.out.println("LINE =" + line);
			float val = Float.parseFloat(line);
			if (val > max) {
				max = val;
			}
			// Traitement du flux de sortie de l'application si besoin est
		}
		return max;
	}

	/**
	 * Calcule du min sur une colonne
	 *
	 * @throws InterruptedException
	 * */
	public Float minValueColumn(int numColumn) throws NumberFormatException,
			IOException, InterruptedException {
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $" + numColumn + "}",
				this.root };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";

		Float min = (float) 999999999;
		while ((line = reader.readLine()) != null) {
			float val = Float.parseFloat(line);
			if (val < min) {
				min = val;
			}
		}
		return min;
	}

	public void factorColumn(int numColumn, int other, float factor,
			String input, String output) throws IOException,
			InterruptedException {
		String[] cmd;
		if (numColumn < other) {
			cmd = new String[] {
					"awk",
					"BEGIN { FS=\",\"; OFS=\",\"; } {print $" + numColumn + "*"
							+ factor + ",$" + other + "}", input };
		} else {
			cmd = new String[] {
					"awk",
					"BEGIN { FS=\",\"; OFS=\",\"; } {print $" + other + ",$"
							+ numColumn + "*" + factor + "}", input };
		}

		CSVWriter writer = new CSVWriter(new FileWriter(output), ',',
				CSVWriter.NO_QUOTE_CHARACTER, Character.MIN_VALUE);
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";

		while ((line = reader.readLine()) != null) {
			String[] val = line.split(",");
			writer.writeNext(val);
		}
		reader.close();
		writer.close();
	}

	/*
	 * Permet de convertir un fichier .dat en .csv
	 */
	public void datToCsv(String input, String output, String header)
			throws IOException, InterruptedException {
		String[] cmd = new String[] {
				this.root + File.separator + "script" + File.separator
						+ "datToCsv", input, output, header };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
		process.waitFor();
	}

	/**
	 * Permet de selctionner un axe en fonction d'un autre
	 *
	 * @param input
	 *            : fichier csv d'entré
	 * @param output
	 *            : fichier csv de sortie
	 * @param x
	 *            axe en abscisse
	 * @param y
	 *            axe en ordonnée
	 * */
	public void selectCurve(String input, String output, int x, int y)
			throws IOException {
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $" + x + ",$" + y + "}",
				input };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

		CSVWriter writer = new CSVWriter(new FileWriter(output), ',',
				CSVWriter.NO_QUOTE_CHARACTER, Character.MIN_VALUE);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";

		while ((line = reader.readLine()) != null) {
			String[] val = line.split(",");
			writer.writeNext(val);
		}
		reader.close();
		writer.close();
	}

	/**
	 * Permet de couper un courbe apres un point
	 *
	 * @param start
	 *            point de coupure
	 * @param input
	 *            chemin du fichier à couper
	 * @throws InterruptedException
	 * */
	public void cutAfter(int start, String input) throws IOException,
			InterruptedException {
		int end = this.nbLigne(input);
		this.deletePortionCsv(input, start, end);
	}

	/**
	 * Permet de couper une courbe avant un point
	 *
	 * @param end
	 *            point de coupure
	 * @param input
	 *            : chemin du fichier csv
	 * @throws InterruptedException
	 * */
	public void cutBefore(int end, String input) throws IOException,
			InterruptedException {
		int start = 1;
		this.deletePortionCsv(input, start, end);
	}

	/**
	 * Retourne le nombre de ligne d'un fichier
	 *
	 * @param input
	 *            : chemin du fichier
	 * @return nombre deligne present dans le fichier
	 * @throws InterruptedException
	 * */
	public int nbLigne(String input) throws IOException, InterruptedException {
		String[] cmd = new String[] { "sed", "-n", "-e", "$=", input };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";
		int result = 0;
		while ((line = reader.readLine()) != null) {

			result = Integer.parseInt(line);
		}
		return result;
	}
}
