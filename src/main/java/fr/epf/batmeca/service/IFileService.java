package fr.epf.batmeca.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;

public interface IFileService {
	/**
	 * Prepare the folder tree for the given test.
	 *
	 * @param test
	 * @throws IOException
	 */
	void initTest(Test test) throws IOException;

	void processTest(Test test, List<TypeTestAttribute> typesTest,
			List<TypeMaterialAttribute> typesMat) throws IOException;

	/**
	 * Remove the folder tree of the given test.
	 *
	 * @param test
	 * @throws IOException
	 */
	void cleanTest(Test test) throws IOException;

	/**
	 * Remove all the tests of the given material.
	 *
	 * @param material
	 * @throws IOException
	 */
	void cleanMaterial(Material material) throws IOException;

	List<String[]> deserializeHeader(Test test) throws IOException;

	/**
	 * List all the curve files for the given test.
	 *
	 * @param test
	 * @return A list of files.
	 */
	File[] listCurve(Test test);

	/**
	 * Return the path to the data file of the given test.
	 *
	 * @param test
	 * @return Path to the data file.
	 */
	String getDataFilename(Test test);

	/**
	 * Return the path to the config file of the given test.
	 *
	 * @param test
	 * @return Path to the config file.
	 */
	String getConfigFilename(Test test);

	/**
	 * Return the path to the folder of the given test.
	 *
	 * @param test
	 * @return Path to the folder.
	 */
	String getTestPath(Test test);

	void saveToJson(List<String[]> list, Test test) throws IOException;

	/**
	 * Add some data to the history file of the given test.
	 *
	 * @param data
	 * @param test
	 * @throws IOException
	 */
	void addHistory(String data, Test test) throws IOException;

	/**
	 * Read the history file of the given test.
	 *
	 * @param test
	 * @return
	 * @throws IOException
	 */
	String readHistory(Test test) throws IOException;

	/**
	 * Add some data to the result file of the given test.
	 *
	 * @param data
	 * @param test
	 * @throws IOException
	 */
	void addResult(String data, Test test) throws IOException;

	/**
	 * Read the result file of the given test.
	 *
	 * @param test
	 * @return The content of the result file (HTML format).
	 * @throws IOException
	 */
	String readResult(Test test) throws IOException;

	String selectCurve(Test test, int x, int y) throws IOException;

	String smooth2ndOrderCurve(String file, Test test) throws IOException;

	String factorColumn(int nbColumn, int other, float factor, String file,
			Test test) throws IOException;

	String resetCurve(Test test) throws IOException;
}
