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
import fr.epf.batmeca.handler.CsvHandler;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.handler.ParserConfig;
import fr.epf.batmeca.service.IMaterialService;
import fr.epf.batmeca.service.ITestService;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.ITypeTestAttributService;
import fr.epf.batmeca.service.IUserService;
import fr.epf.batmeca.service.impl.ValueServiceImpl;

@Controller
public class TestController {

	@Autowired
	private ValueServiceImpl valueService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ITestService testService;
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
		CsvHandler csv = new CsvHandler(valueService.getRoot() + File.separator
				+ valueService.getName());
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		ArrayList<String[]> list = f.deserializeFileJson(f.getPathSave(t)
				+ File.separator + "header.json");

		// String data =
		// csv.readAll(f.getPathSave(t)+File.separator+"dataInput.csv");

		// Recuperation des données et du nom des fichiers
		File[] files = f.listCurve(t);
		ArrayList<String[]> listData = new ArrayList<String[]>();
		ArrayList<String> listFile = new ArrayList<String>();

		for (File file : files) {
			System.out.println("NAME = " + file.getAbsolutePath());
			listFile.add(file.getAbsolutePath());
			String[] tab = { csv.readAll(file.getAbsolutePath()),
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
			@RequestParam("file") MultipartFile[] files, Principal principal)
			throws IOException {

		List<TypeTestAttribute> typesTest = typeTestService.findAll();
		List<TypeMaterialAttribute> typesMat = typeMatService.findAll();
		Material mat = materialService.find(Integer.parseInt(idMat));
		CsvHandler csv = new CsvHandler(valueService.getRoot() + File.separator
				+ valueService.getName());

		Test test = new Test();
		test.setName(name);
		test.setDate(new Date());
		test.setMaterial(mat);
		test.setTestAttributs(new HashSet<TestAttribute>());

		User user = userService.getUser(principal.getName());
		test.setUser(user);

		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		f.initDirectory(test);
		String savePath = f.getPathSave(test);

		// Upload des fichiers
		for (int i = 0; i < files.length && i < 2; i++) {
			MultipartFile file = files[i];
			String filename = file.getName();
			String path = savePath + File.separator;

			System.out.println("FILE NAME = " + filename);
			if (i == 0) {
				path += "data" + File.separator + filename;
			} else {
				path += "config" + File.separator + filename;
			}

			try {
				File serverFile = new File(path);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}

		testService.add(test);
		// Conversion du fichier data en csv
		try {
			csv.datToCsv(f.getPathSave(test) + File.separator + "data"
					+ File.separator + f.getFileNameData(test),
					f.getPathSave(test) + File.separator + "dataInput.csv",
					f.getPathSave(test) + File.separator + "header.txt");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ParserConfig prconf = new ParserConfig();
		test = prconf.parseFileConfig(valueService.getResourcePath(), test,
				f.getPathSave(test) + File.separator + "config"
						+ File.separator + f.getFileNameConfig(test), typesMat,
				typesTest);
		prconf.parseHeader(f.getPathSave(test) + File.separator + "header.txt",
				f.getPathSave(test) + File.separator + "header.json");

		return "redirect:/ShowTest?idTest=" + test.getId();
	}

	@RequestMapping(value = "/RemoveTest", method = RequestMethod.GET)
	protected String removeTestGet(@RequestParam(value = "id") String id,
			@RequestParam(value = "idMat") String idMat) throws IOException {

		Test t = testService.find(Integer.parseInt(id));
		FolderHandler f = new FolderHandler(valueService.getResourcePath());

		f.deleteFolder(t);
		testService.remove(t);

		return "redirect:/Material?idMat=" + idMat;
	}
}
