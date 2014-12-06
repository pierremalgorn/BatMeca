package fr.epf.batmeca.service.impl;

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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import fr.epf.batmeca.config.Config;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.handler.ParserConfig;
import fr.epf.batmeca.service.IFileService;

@Service
public class FileService implements IFileService {

	private static final String S = File.separator;
	// Directories
	private static final String CONFIG_D = S + "config";
	private static final String DATA_D = S + "data";
	private static final String HISTORY_D = S + "history";
	private static final String CURVE_D = S + "curve";
	// Files
	private static final String CONFIG_F = CONFIG_D + S + "config";
	private static final String DATA_F = DATA_D + S + "data";
	private static final String HISTORY_F = HISTORY_D + S + "historic";
	private static final String RESULT_F = S + "result";
	private static final String HEADER_F = S + "header.json";

	@Autowired
	private Config config;

	@Override
	public void initTest(Test test) throws IOException {
		String path = getTestPath(test);
		System.out.println(path);

		new File(path).mkdirs();

		new File(path + CONFIG_D).mkdir();
		new File(path + DATA_D).mkdir();
		new File(path + HISTORY_D).mkdir();
		new File(path + CURVE_D).mkdir();

		new File(path + CONFIG_F).createNewFile();
		new File(path + DATA_F).createNewFile();
		new File(path + HISTORY_F).createNewFile();
		new File(path + RESULT_F).createNewFile();
	}

	@Override
	public void processTest(Test test, List<TypeTestAttribute> typesTest,
			List<TypeMaterialAttribute> typesMat) throws IOException {
		// Convert from dat to csv format
		String[] cmd = new String[] {
				config.getProjectPath() + File.separator + "script"
						+ File.separator + "datToCsv", getDataFilename(test),
				getTestPath(test) + File.separator + "dataInput.csv",
				getTestPath(test) + File.separator + "header.txt" };
		Runtime runtime = Runtime.getRuntime();
		final Process process = runtime.exec(cmd);
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Extract header
		test = ParserConfig.parseFileConfig(test, getConfigFilename(test),
				typesMat, typesTest);
		ParserConfig.parseHeader(getTestPath(test) + File.separator
				+ "header.txt", getTestPath(test) + File.separator
				+ "header.json");
	}

	@Override
	public void cleanTest(Test test) throws IOException {
		FileUtils.deleteDirectory(new File(getTestPath(test)));
	}

	@Override
	public void cleanMaterial(Material material) throws IOException {
		for (Test test : material.getTests()) {
			cleanTest(test);
		}
	}

	@Override
	public List<String[]> deserializeHeader(Test test) throws IOException {
		List<String[]> list = new ArrayList<String[]>();
		InputStream ips = new FileInputStream(getTestPath(test) + HEADER_F);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		Gson gson = new Gson();
		String ligne;
		while ((ligne = br.readLine()) != null) {
			List<String[]> l = gson.fromJson(ligne, ArrayList.class);
			list.addAll(l);
		}
		br.close();
		return list;
	}

	@Override
	public File[] listCurve(Test test) {
		File file = new File(getTestPath(test) + CURVE_D);
		File[] files = file.listFiles();
		if (files == null) {
			// TODO handle this case (which means IOException)
			files = new File[0];
		} else {
			Arrays.sort(files);
		}
		return files;
	}

	@Override
	public String getDataFilename(Test test) {
		return getTestPath(test) + DATA_F;
	}

	@Override
	public String getConfigFilename(Test test) {
		return getTestPath(test) + CONFIG_F;
	}

	@Override
	public String getTestPath(Test test) {
		// FIXME two tests with the same name will overlap
		return config.getResourcePath() + S + test.getName();
	}

	@Override
	public void saveToJson(List<String[]> list, String output)
			throws IOException {
		writeToFile(new Gson().toJson(list), output, false);
	}

	@Override
	public void renameFile(String from, String to) {
		new File(from).renameTo(new File(to)); // XXX
	}

	@Override
	public void addHistory(String data, Test test) throws IOException {
		writeToFile(data, getTestPath(test) + HISTORY_F, true);
	}

	@Override
	public String readHistory(Test test) throws IOException {
		InputStream ips = new FileInputStream(getTestPath(test) + HISTORY_F);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		StringBuilder sb = new StringBuilder();
		String ligne;
		while ((ligne = br.readLine()) != null) {
			sb.append(ligne);
			sb.append("<br>");
		}
		br.close();
		return new Gson().toJson(sb.toString());
	}

	@Override
	public void addResult(String data, Test test) throws IOException {
		writeToFile(data, getTestPath(test) + RESULT_F, true);
	}

	@Override
	public String readResult(Test test) throws IOException {
		InputStream ips = new FileInputStream(getTestPath(test) + RESULT_F);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		StringBuilder sb = new StringBuilder();
		String ligne;
		while ((ligne = br.readLine()) != null) {
			sb.append(ligne);
			sb.append("<br>");
		}
		br.close();
		return new Gson().toJson(sb.toString());
	}

	private void writeToFile(String string, String file, boolean append)
			throws IOException {
		FileWriter fw = new FileWriter(file, append);
		PrintWriter pr = new PrintWriter(new BufferedWriter(fw));
		pr.println(string);
		pr.close();
	}
}