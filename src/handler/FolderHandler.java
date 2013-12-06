package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import entity.Material;
import entity.Test;

public class FolderHandler {

	private static String root;
	private File file;

	public FolderHandler() {
		super();
		root = "/home/max/BatMeca/Ressources";
		// file = new File(root+"/toto/tutu");
		// file.mkdirs();
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

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
		return this.root + "/" + test.getName();
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
	 * @param test: essai à initialisé
	 * */
	public void initDirectory(Test test) throws IOException {

		String path = this.getPathSave(test);

		File f = new File(path);
		f.mkdirs();
		this.makeDir(path + "/config");
		this.makeDir(path + "/data");
		this.makeDir(path + "/history");
		this.makeDir(path + "/curve");
		File fh = new File(path + "/history/historic");
		fh.createNewFile();
		
	}

	public File[] getListDir(String path) {
		File f = new File(path);
		return f.listFiles();
	}

	public String getFileNameData(Test t) {
		File file = new File(this.getPathSave(t) + "/data");
		File[] files = file.listFiles();

		/*
		 * for (File f : files) { String name = f.getName(); //
		 * System.out.println("NAME FILE = "+name); }
		 */

		return files[files.length - 1].getName();
	}

	public String getFileNameConfig(Test t) {
		File file = new File(this.getPathSave(t) + "/config");
		File[] files = file.listFiles();

		/*
		 * for (File f : files) { String name = f.getName(); //
		 * System.out.println("NAME FILE = "+name); }
		 */

		return files[files.length - 1].getName();
	}

	public void cleanAllTestDir(Material mat) throws IOException {
		for (Test test : mat.getTests()) {
			this.deleteFolder(test);
		}
	}

	/*
	 * Marche pas bien
	 */
	public void cleanMatFolder(Material mat) throws IOException {

		this.cleanAllTestDir(mat);
		if (mat.getMaterialParent() != null) {
			while (mat.getMaterialParent() != null) {
				mat = mat.getMaterialParent();
				cleanAllTestDir(mat);
			}
			cleanAllTestDir(mat);
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
	 * Permet de sauver un  liste dans un fichier json
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
	 * */
	public void addDataHistoryFile(String data,Test test) throws IOException{
		PrintWriter pr;

		pr = new PrintWriter(new BufferedWriter(new FileWriter(this.getPathSave(test)+"/history/historic",true)));
		pr.println(data);
		//pr.write(data);
		pr.close();
	}
	
	/**
	 * permet de renommer le fichier de sortie d'un essai
	 * */
	public void renameCsvOutput(Test test){
		File file = new File(this.getPathSave(test)+"/dataOutput.csv");
		file.renameTo(new File(this.getPathSave(test)+"/dataInput.csv"));
	}
	
	public File[] listCurve(Test test){
		
		File file = new File(this.getPathSave(test) + "/curve");
		File[] files = file.listFiles();
		Arrays.sort(files);
		return files;
	}
	
	
}
