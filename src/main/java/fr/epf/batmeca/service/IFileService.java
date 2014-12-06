package fr.epf.batmeca.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;

public interface IFileService {
	void initTest(Test test) throws IOException;

	void processTest(Test test, List<TypeTestAttribute> typesTest,
			List<TypeMaterialAttribute> typesMat) throws IOException;

	void cleanTest(Test test) throws IOException;

	void cleanMaterial(Material material) throws IOException;

	List<String[]> deserializeHeader(Test test) throws IOException;

	File[] listCurve(Test test);

	String getDataFilename(Test test);

	String getConfigFilename(Test test);

	String getTestPath(Test test);

	void saveToJson(List<String[]> list, Test test) throws IOException;

	void addHistory(String data, Test test) throws IOException;

	String readHistory(Test test) throws IOException;

	void addResult(String data, Test test) throws IOException;

	String readResult(Test test) throws IOException;

	String selectCurve(Test test, int x, int y) throws IOException;

	String lissageOrdre2(String file, Test test) throws IOException;

	String factorColumn(int nbColumn, int other, float factor, String file,
			Test test) throws IOException;

	String resetCurve(Test test) throws IOException;
}
