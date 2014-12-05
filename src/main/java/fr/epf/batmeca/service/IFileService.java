package fr.epf.batmeca.service;

import java.io.File;
import java.util.List;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;

public interface IFileService {
	void initTest(Test test);

	void cleanTest(Test test);

	void cleanMaterial(Material material);

	List<String[]> deserializeHeader(Test test);

	File[] listCurve(Test test);

	String getDataFilename(Test test);

	String getConfigFilename(Test test);

	String getTestPath(Test test);

	void saveToJson(List<String[]> list, String output);

	void renameFile(String from, String to);

	void addHistory(String data, Test test);

	String readHistory(Test test);

	void addResult(String data, Test test);

	String readResult(Test test);
}
