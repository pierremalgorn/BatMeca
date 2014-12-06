package fr.epf.batmeca.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.TestAttribute;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.helper.CsvHandler;
import fr.epf.batmeca.service.IFileService;
import fr.epf.batmeca.service.IMaterialService;
import fr.epf.batmeca.service.ITestService;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.ITypeTestAttributService;
import fr.epf.batmeca.service.IUserService;

@Controller
public class TestController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITestService testService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private IMaterialService materialService;
	@Autowired
	private ITypeMaterialAttributService typeMatService;
	@Autowired
	private ITypeTestAttributService typeTestService;

	@RequestMapping(value = "/ShowTest", method = RequestMethod.GET)
	protected String showTestGet(@RequestParam(value = "idTest") String idTest,
			ModelMap model) throws IOException {

		int id = Integer.parseInt(idTest);
		Test t = testService.find(id);
		List<String[]> list = fileService.deserializeHeader(t);

		// String data =
		// csv.readAll(f.getPathSave(t)+File.separator+"dataInput.csv");

		// Recuperation des données et du nom des fichiers
		File[] files = fileService.listCurve(t);
		List<String[]> listData = new ArrayList<String[]>();
		List<String> listFile = new ArrayList<String>();

		for (File file : files) {
			System.out.println("NAME = " + file.getAbsolutePath());
			listFile.add(file.getAbsolutePath());
			String[] tab = { CsvHandler.readAll(file.getAbsolutePath()),
					file.getAbsolutePath() };
			listData.add(tab);
		}

		model.addAttribute("colHeader", list);
		model.addAttribute("files", files);
		// model.addAttribute("data", data);
		model.addAttribute("listData", listData);
		model.addAttribute("test", t);
		model.addAttribute("listFile", new Gson().toJson(listFile));
		// model.addAttribute("listCol", listCol);

		return "test";
	}

	@RequestMapping(value = "/IndexTest", method = RequestMethod.GET)
	protected String indexTestGet(ModelMap model) {
		List<Test> tests = testService.findAll();

		model.addAttribute("tests", tests);

		return "indexTest";
	}

	@RequestMapping(value = "/AddTest", method = RequestMethod.GET)
	protected String addTestGet(@RequestParam(value = "idMat") String idMat,
			ModelMap model) {
		List<TypeTestAttribute> typesTest = typeTestService.findAll();

		model.addAttribute("typesTest", typesTest);
		model.addAttribute("idMat", idMat);

		return "addTest";
	}

	@RequestMapping(value = "/AddTest", method = RequestMethod.POST)
	protected String addTestPost(
			@RequestParam(value = "inputNameTest") String name,
			@RequestParam(value = "idMat") String idMat,
			@RequestParam("inputDataFile") MultipartFile dataFile,
			@RequestParam("inputConfigFile") MultipartFile configFile,
			Principal principal) throws IOException {

		Material mat = materialService.find(Integer.parseInt(idMat));

		Test test = new Test();
		test.setName(name);
		test.setDate(new Date());
		test.setMaterial(mat);
		test.setTestAttributs(new HashSet<TestAttribute>());

		User user = userService.getUser(principal.getName());
		test.setUser(user);

		fileService.initTest(test);

		// File upload
		try {
			File serverFile = new File(fileService.getDataFilename(test));
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(dataFile.getBytes());
			stream.close();
		} catch (Exception e) {
			return "You failed to upload DATA file => " + e.getMessage();
		}
		try {
			File serverFile = new File(fileService.getConfigFilename(test));
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(configFile.getBytes());
			stream.close();
		} catch (Exception e) {
			return "You failed to upload CONFIG file => " + e.getMessage();
		}

		testService.add(test);

		// Convert from dat to csv file format
		List<TypeTestAttribute> typesTest = typeTestService.findAll();
		List<TypeMaterialAttribute> typesMat = typeMatService.findAll();
		fileService.processTest(test, typesTest, typesMat);

		return "redirect:/ShowTest?idTest=" + test.getId();
	}

	@RequestMapping(value = "/RemoveTest", method = RequestMethod.GET)
	protected String removeTestGet(@RequestParam(value = "id") String id,
			@RequestParam(value = "idMat") String idMat) throws IOException {
		Test t = testService.find(Integer.parseInt(id));
		testService.remove(t);
		return "redirect:/Material?idMat=" + idMat;
	}
}
