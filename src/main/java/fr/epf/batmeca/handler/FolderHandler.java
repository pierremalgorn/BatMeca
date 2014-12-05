package fr.epf.batmeca.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;

public class FolderHandler {

	@Value("${project.root}")
	private String root;

	/**
	 * useless
	 * */
	public void makeDir(String path) {
		File f = new File(path);
		f.mkdir();
	}

	/**
	 * Permet de recuperer les chemin de sauvegarde du Test
	 * */
	public String getPathSave(Test test) {
		return root + File.separator + test.getName();
	}

	/**
	 * Permet de supprimer le dossier de sauvegarde d'un test
	 * */
	public void deleteFolder(Test test) throws IOException {
		String[] cmd = new String[] { "rm", "-r", this.getPathSave(test) };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
	}

	/**
	 * Permet de creer les repertoires relatif à une essai
	 *
	 * @param test
	 *            : essai à initialisé
	 * */
	public void initDirectory(Test test) throws IOException {
		String path = this.getPathSave(test);

		File f = new File(path);
		f.mkdirs();
		this.makeDir(path + File.separator + "config");
		this.makeDir(path + File.separator + "data");
		this.makeDir(path + File.separator + "history");
		this.makeDir(path + File.separator + "curve");
		File fh = new File(path + File.separator + "history" + File.separator
				+ "historic");
		fh.createNewFile();

		File fr = new File(path + File.separator + "result");
		fr.createNewFile();
	}

	public File[] getListDir(String path) {
		File f = new File(path);
		return f.listFiles();
	}

	public String getDataFilename(Test t) {
		return getPathSave(t) + File.separator + "data" + File.separator + t.getId();
//		File file = new File(this.getPathSave(t) + File.separator + "data");
//		File[] files = file.listFiles();
//
//		/*
//		 * for (File f : files) { String name = f.getName(); //
//		 * System.out.println("NAME FILE = "+name); }
//		 */
//
//		return files[files.length - 1].getName();
	}

	public String getConfigFilename(Test t) {
		return getPathSave(t) + File.separator + "config" + File.separator + t.getId();
//		File file = new File(this.getPathSave(t) + File.separator + "config");
//		File[] files = file.listFiles();
//
//		/*
//		 * for (File f : files) { String name = f.getName(); //
//		 * System.out.println("NAME FILE = "+name); }
//		 */
//
//		return files[files.length - 1].getName();
	}

	public void cleanAllTestDir(Material mat) throws IOException {
		for (Test test : mat.getTests()) {
			this.deleteFolder(test);
		}
	}

	/*
	 * Marche pas bien
	 */
	public void cleanMatFolder(Material mat) {
		try {
			this.cleanAllTestDir(mat);
			if (mat.getMaterialParent() != null) {
				while (mat.getMaterialParent() != null) {
					mat = mat.getMaterialParent();
					cleanAllTestDir(mat);
				}
				cleanAllTestDir(mat);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] parseHeader(String input) throws IOException {
		InputStream ips = new FileInputStream(input);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne = "";
		String[] header = new String[3];
		int cpt = 0;
		while (cpt < 3 && (ligne = br.readLine()) != null) {

			header[cpt] = ligne;
			cpt++;
		}
		br.close();
		return header;
	}

	/**
	 * Permet de parser le header de l'essai
	 *
	 * @param input
	 *            chemin du fichier à parser
	 * */
	public ArrayList<String[]> getheaderColumn(String input) throws IOException {
		String[] tab = this.parseHeader(input);
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (int i = 1; i < tab.length; i++) {

			tab[i] = tab[i].replaceAll("#", "");
			String[] row = tab[i].split("\t");
			for (int j = 0; j < row.length; j++) {
				if (j == 0) {
					row[j] = row[j].replaceAll(" ", "");
				}
			}
			list.add(row);
		}

		return list;
	}

	/**
	 * Permet de sauver un liste dans un fichier json
	 * */
	public void saveToJson(ArrayList<String[]> list, String output)
			throws IOException {
		PrintWriter pr;
		pr = new PrintWriter(new BufferedWriter(new FileWriter(output)));
		pr.println(new Gson().toJson(list));
		pr.close();
	}

	/**
	 * Permet d'ajouter un information au fichier d'historique d'un essai
	 *
	 * @param data
	 *            information a rajouter au fichier
	 * @param test
	 *            test courant
	 * */
	public void addDataHistoryFile(String data, Test test) throws IOException {
		PrintWriter pr;
		pr = new PrintWriter(new BufferedWriter(new FileWriter(
				this.getPathSave(test) + File.separator + "history"
						+ File.separator + "historic", true)));
		pr.println(data);
		// pr.write(data);
		pr.close();
	}

	/**
	 * Permet d'ajouter un resulat
	 *
	 * @param data
	 *            : information a rajouter
	 * @param test
	 *            : test courant
	 * */
	public void addResult(String data, Test test) throws IOException {
		PrintWriter pr;
		pr = new PrintWriter(new BufferedWriter(new FileWriter(
				this.getPathSave(test) + File.separator + "result", true)));
		pr.println(data);
		pr.close();
	}

	/**
	 * permet de renommer le fichier de sortie d'un essai
	 * */
	public void renameCsvOutput(Test test) {
		File file = new File(this.getPathSave(test) + File.separator
				+ "dataOutput.csv");
		file.renameTo(new File(this.getPathSave(test) + File.separator
				+ "dataInput.csv"));
	}

	/**
	 * Methode permettant de renvoyer la liste des fichiers de courbes
	 *
	 * @param test
	 * */
	public File[] listCurve(Test test) {
		File file = new File(this.getPathSave(test) + File.separator + "curve");
		File[] files = file.listFiles();
		Arrays.sort(files);
		return files;
	}

	/**
	 * Methode permettant de renomer un fichier
	 *
	 * @param input
	 *            fichier à renommer
	 * @param output
	 *            nom de fichier de sortie
	 * */
	public void renameFile(String input, String output) {
		File file = new File(input);
		file.renameTo(new File(output));
	}

	/**
	 * Permet de deserializer un fichier Json
	 *
	 * @param input
	 *            path du fichier à lire
	 * */
	public ArrayList<String[]> deserializeFileJson(String input)
			throws IOException {
		InputStream ips = new FileInputStream(input);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne = "";
		Gson gson = new Gson();
		ArrayList<String[]> list = null;
		while ((ligne = br.readLine()) != null) {
			System.out.println(ligne);
			list = gson.fromJson(ligne, ArrayList.class);
		}
		br.close();
		return list;
	}

	public String readResult(Test test) throws IOException {
		String data = "";

		InputStream ips = new FileInputStream(this.getPathSave(test)
				+ File.separator + "result");
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne = "";
		while ((ligne = br.readLine()) != null) {
			data = data + ligne + "<br>";
		}
		br.close();

		return new Gson().toJson(data);
	}

	public String readHistoric(Test test) throws IOException {
		String data = "";

		InputStream ips = new FileInputStream(this.getPathSave(test)
				+ File.separator + "history" + File.separator + "historic");
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne = "";
		while ((ligne = br.readLine()) != null) {
			data = data + ligne + "<br>";
		}
		br.close();

		return new Gson().toJson(data);
	}
}
