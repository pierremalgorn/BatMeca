package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import entity.Test;

public class RuntimeHandler {

	public RuntimeHandler() {

	}

	public void execScript() {
		Runtime runtime = Runtime.getRuntime();
		try {

			final Process process = runtime
					.exec(new String[] { "/home/max/BatMeca/Ressources/script/scriptTest","matTest/subTest/testToto/","X65_ST_L_1.par","X65_ST_L_1.txt" });

			// Consommation de la sortie standard de l'application externe dans
			// un Thread separe
			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(process.getInputStream()));
						String line = "";
						try {
							while ((line = reader.readLine()) != null) {
								System.out.println("LINE = "+line);
								// Traitement du flux de sortie de l'application
								// si besoin est
							}
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
								System.out.println("LINE err= "+line);
							}
						} finally {
							reader.close();
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
