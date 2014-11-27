package fr.epf.batmeca.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import fr.epf.batmeca.service.impl.ValueServiceImpl;

/**
 * Servlet implementation class AddTestServlet
 */
@Controller
@RequestMapping("/AddTest")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class AddTestServlet {

	@Autowired
	private ValueServiceImpl valueService;
	@Autowired
	private ITestService testService;
	@Autowired
	private IMaterialService materialService;
	@Autowired
	private ITypeMaterialAttributService typeMatService;
	@Autowired
	private ITypeTestAttributService typeTestService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

		HttpSession session = request.getSession();
		test.setUser((User) session.getAttribute("sessionUser"));

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
}
