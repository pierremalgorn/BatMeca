package fr.epf.batmeca.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	protected void showTestGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int idTest = Integer.parseInt(request.getParameter("idTest"));
		Test t = testService.find(idTest);
		CsvHandler csv = new CsvHandler(valueService.getRoot() + File.separator
				+ valueService.getName());

		FolderHandler f = new FolderHandler(valueService.getResourcePath());

		// Récuperation de l'header
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

		request.setAttribute("colHeader", list);

		request.setAttribute("files", files);
		// request.setAttribute("data", data);
		request.setAttribute("listData", listData);
		request.setAttribute("test", t);
		request.setAttribute("listFile", new Gson().toJson(listFile));
		// request.setAttribute("listCol", listCol);

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/test.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/ShowTest", method = RequestMethod.POST)
	protected void showTestPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value = "/IndexTest", method = RequestMethod.GET)
	protected void indexTestGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Récuperation de la liste des essais associés
		 */
		List<Test> tests;
		tests = testService.findAll();

		request.setAttribute("tests", tests);

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/indexTest.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/IndexTest", method = RequestMethod.POST)
	protected void indexTestPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@RequestMapping(value = "/AddTest", method = RequestMethod.GET)
	protected void addTestGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// récupération de la liste des attributs du test
		List<TypeTestAttribute> typesTest;
		typesTest = typeTestService.findAll();

		request.setAttribute("typesTest", typesTest);
		request.setAttribute("idMat", request.getParameter("idMat"));
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addTest.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/AddTest", method = RequestMethod.POST)
	protected void addTestPost(HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws ServletException, IOException {
		// récupération des champs du formulaires
		String name = request.getParameter("inputNameTest");
		List<TypeTestAttribute> typesTest;
		typesTest = typeTestService.findAll();
		List<TypeMaterialAttribute> typesMat = typeMatService.findAll();
		/**
		 * Recuperation du materiel associé
		 */
		Material mat = materialService.find(Integer.parseInt(request
				.getParameter("idMat")));
		CsvHandler csv = new CsvHandler(valueService.getRoot() + File.separator
				+ valueService.getName());

		Test test = new Test();
		test.setName(name);
		test.setDate(new Date());
		test.setMaterial(mat);

		// Ajout des attributs

		test.setTestAttributs(new HashSet<TestAttribute>());

		User user = userService.getUser(principal.getName());
		test.setUser(user);

		// Création de repertoire associer

		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		f.initDirectory(test);
		String savePath = f.getPathSave(test);
		// Upload des fichiers
		int cpt = 0;
		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			System.out.println("FILE NAME = " + fileName);

			if (fileName.compareTo("") != 0) {
				if (cpt == 0) {
					part.write(savePath + File.separator + "data"
							+ File.separator + fileName);
					cpt++;
				} else {
					part.write(savePath + File.separator + "config"
							+ File.separator + fileName);
				}
			}
			// part.write(fileName);
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
		/*
		 * Parsind du fichier de configuration
		 */
		ParserConfig prconf = new ParserConfig();

		test = prconf.parseFileConfig(valueService.getResourcePath(), test,
				f.getPathSave(test) + File.separator + "config"
						+ File.separator + f.getFileNameConfig(test), typesMat,
				typesTest);

		prconf.parseHeader(f.getPathSave(test) + File.separator + "header.txt",
				f.getPathSave(test) + File.separator + "header.json");
		// Enregistrement en base de données

		response.sendRedirect(response.encodeURL("./ShowTest?idTest="
				+ test.getId()));
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	@RequestMapping(value = "/RemoveTest", method = RequestMethod.GET)
	protected void removeTestGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Test t = testService.find(Integer.parseInt(id));
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		f.deleteFolder(t);
		testService.remove(t);

		response.sendRedirect(response.encodeURL("./Material?idMat="
				+ request.getParameter("idMat")));
	}

	@RequestMapping(value = "/RemoveTest", method = RequestMethod.POST)
	protected void removeTestPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
