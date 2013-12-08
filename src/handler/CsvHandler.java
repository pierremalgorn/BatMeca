package handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;


import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class CsvHandler {

	private String fileInput;
	
	
	public CsvHandler(){
		
	}
	
	public CsvHandler(String input){
		fileInput = input;

	}
	
	
	/**
	 * Permet de renvoyé une chaine de caractere contenant l'integralité d'un fichier csv
	 * @param input fichier a lire
	 * */
	public String readAll(String input) throws IOException {
		//CSVReader reader = new CSVReader(new FileReader(input),',','"',2);
		CSVReader reader = new CSVReader(new FileReader(input),',','"');	
		StringBuilder sb = new StringBuilder();
		List<String[]> myEntries = reader.readAll();
		for (String[] strings : myEntries) {
			for (String string : strings) {
				sb.append(string+",");
			}
			sb.append("\n");
			
		}
		reader.close();
		//System.out.println(sb.toString());
		return new Gson().toJson(sb.toString());
	}

	/**
	 * Permet l'echantillonage du fichier csv
	 * */
	public void echantillon(int echant) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(
				"/home/max/BatMeca/data.csv"),',','"',2);
		CSVWriter writer = new CSVWriter(new FileWriter(
				"/home/max/BatMeca/data1.csv"), ',');
		List<String[]> myEntries = reader.readAll();
		for (int i = 0; i < myEntries.size(); i = i + echant) {
			// System.out.println("i = "+i);
			writer.writeNext(myEntries.get(i));

		}
		reader.close();
		writer.close();
	}

	
	public void testCutCsv(int start, int end) throws IOException {
		String[] cmd = new String[] { "/usr/bin/cut", "--delimiter=,", "-f3-5",
				"/home/max/BatMeca/data.csv" };
		Runtime runtime = Runtime.getRuntime();

		final Process process = runtime.exec(cmd);
		new Thread() {
			public void run() {
				CSVWriter writer = null;
				try {
					writer = new CSVWriter(new FileWriter(
							"/home/max/BatMeca/data1.csv"), ',');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					String line = "";
					try {
						while ((line = reader.readLine()) != null) {
							System.out.println("LINE = " + line);
							String[] entries = line.split(",");
							writer.writeNext(entries);

						}
						writer.close();
					} finally {
						reader.close();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}.start();
	}
	/**
	 * Permet de faire la moyenne entre deux points
	 * */
	public float average(float f1, float f2) {
		return (f1 + f2) / 2;
	}

	/**
	 * Lissage des points
	 * */
	public void lissageOrdre2(String input,String output) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(input), ',','"',Character.MIN_VALUE);
		CSVWriter writer = new CSVWriter(new FileWriter(output), ',','"',Character.MIN_VALUE);
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

	/*
	 * Séparer les colonnes d'un fichier csv 
	 * pas operationnel
	 */
	public void switchColomn() throws IOException {
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $1}",
				"/home/max/BatMeca/data.csv" };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
		new Thread() {
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					String line = "";
					try {
						Float max = (float) -999999999;
						while ((line = reader.readLine()) != null) {
							System.out.println("LINE = " + line);
							float val = Float.parseFloat(line);
							if (val > max) {
								max = val;
							}
							// Traitement du flux de sortie de l'application
							// si besoin est
						}
						System.out.println("MAX = " + max);
					} finally {
						reader.close();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}.start();

		// Consommation de la sortie d'erreur de l'application externe dans
		// un Thread separe
		new Thread() {
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(process.getErrorStream()));
					String line = "";
					try {
						while ((line = reader.readLine()) != null) {
							// Traitement du flux d'erreur de l'application
							// si besoin est
							System.out.println("LINE err= " + line);
						}
					} finally {
						reader.close();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * Permet de supprimer un interval de point
	 * */
	public void deletePortionCsv(String input,int start, int end) throws IOException {
		String[] cmd = new String[] { "/bin/sed", "-i", "-e",
				start + "," + end + "d", input };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

	}

	public Float maxValueColumn(int numColumn,String input) throws NumberFormatException, IOException {
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $"+numColumn+"}",
				input };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";
		
		reader.readLine();
		Float max = (float) -999999999;
		while ((line = reader.readLine()) != null) {
			System.out.println("LINE ="+line);
			float val = Float.parseFloat(line);
			if (val > max) {
				max = val;
			}
			// Traitement du flux de sortie de l'application
		} // si besoin est
		return max;
	}
	/**
	 * Calcule du min sur une colonne
	 * */
	public Float minValueColumn(int numColumn) throws NumberFormatException, IOException {
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $"+numColumn+"}",
				this.fileInput };
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
	
	public void factorColumn(int numColumn,float factor,String input,String output) throws IOException{
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $"+numColumn+"*"+factor+"}",
				input};
		CSVWriter writer = new CSVWriter(new FileWriter(
				output), ',');
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";

	
		while ((line = reader.readLine()) != null) {
			String[] val =  line.split(",");
			writer.writeNext(val);
		} 
		reader.close();
		writer.close();
	}
	
	/*
	 * Permet de convertir un fichier .dat en .csv
	 * */
	public void datToCsv(String input,String output) throws IOException{
		String[] cmd = new String[] { "/home/max/BatMeca/BatmecaNewGeneration/script/datToCsv",input,output
				 };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
	}
	
	/**
	 * Permet de selctionner un axe en fonction d'un autre
	 * @param input: fichier csv d'entré
	 * @param output: fichier csv de sortie
	 * @param x axe en abscisse
	 * @param y axe en ordonnée
	 * */
	public void selectCurve(String input,String output,int x,int y) throws IOException{
		String[] cmd = new String[] { "awk",
				"BEGIN { FS=\",\"; OFS=\",\"; } {print $"+x+",$"+y+"}",
				input};
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
		CSVWriter writer = new CSVWriter(new FileWriter(
				output), ',',CSVWriter.NO_QUOTE_CHARACTER ,Character.MIN_VALUE);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = "";

		while ((line = reader.readLine()) != null) {
			String[] val =  line.split(",");
			writer.writeNext(val);
		} 
		reader.close();
		writer.close();
	}
	
	
	

}
