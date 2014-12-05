package fr.epf.batmeca.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.MaterialAttribute;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.service.IMaterialService;
import fr.epf.batmeca.service.ITestService;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.IUserService;
import fr.epf.batmeca.service.impl.ValueServiceImpl;

@Controller
public class MaterialController {

	@Autowired
	private IMaterialService materialService;
	@Autowired
	private ITypeMaterialAttributService typeMaterialAttributService;
	@Autowired
	private ITestService testService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ValueServiceImpl valueService;

	@RequestMapping(value = "/Material", method = RequestMethod.GET)
	protected void materialGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Récupération de la liste des matériaux
		 */
		String id = request.getParameter("idMat");
		Material mat = null;
		List<Material> childs = null;

		mat = materialService.find(Integer.parseInt(id));
		childs = materialService.findByParent(mat);
		System.out.println("NB child = " + childs.size());
		request.setAttribute("material", mat);
		request.setAttribute("childs", childs);

		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/material.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/Material", method = RequestMethod.POST)
	protected void materialPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value = "/IndexMaterial", method = RequestMethod.GET)
	protected String indexMaterialGet(ModelMap model, Principal principal) {
		List<Material> list = null;

		User user = userService.getUser(principal.getName());
		if (user.getType().getId() == 1) {
			list = materialService.findAllNoParent();
		} else {
			list = materialService.findByUser(user);
		}

		model.addAttribute("materials", list);
		return "indexMaterial";
	}

	@RequestMapping(value = "/IndexMaterial", method = RequestMethod.POST)
	protected void indexMaterialPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value = "/addMaterial", method = RequestMethod.GET)
	protected void addMaterialGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Material> materials;
		// Récupération de la liste des materiaux
		materials = materialService.findAll();
		// Récupération de la liste des attribuuts d'un matériaux
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();

		request.setAttribute("mats", materials);
		request.setAttribute("matAttrs", listAttr);
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addMaterial.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/addMaterial", method = RequestMethod.POST)
	protected String addMaterialPost(
			@RequestParam(value = "inputName") String name,
			@RequestParam(value = "inputMaterialParent") String parent,
			HttpServletRequest request, Principal principal) {
		// Récuperation des champs
		Material mat = new Material();
		mat.setName(name);

		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();

		mat.setMatAttrs(new HashSet<MaterialAttribute>());

		// Création des attributs du matériel
		for (TypeMaterialAttribute tMatAttr : listAttr) {
			MaterialAttribute matAttr = new MaterialAttribute();
			String nameAttr = request
					.getParameter("input" + tMatAttr.getName());
			if (nameAttr.compareTo("") != 0) {
				System.out.println("add attr");
				matAttr.setValue(nameAttr);
				matAttr.setTypeMatAttr(tMatAttr);
				matAttr.setMaterial(mat);
				mat.addMaterialAttribute(matAttr);
			}
		}

		if (parent.compareTo("") != 0) {
			Material matParent = materialService.find(Integer.parseInt(parent));
			mat.setMaterialParent(matParent);
		}

		// association du materiel a l'utilisateur courant
		User user = userService.getUser(principal.getName());
		mat.setUser(user);

		materialService.addMaterial(mat);

		return "redirect:/IndexMaterial";
	}

	@RequestMapping(value = "/EditMaterial", method = RequestMethod.GET)
	protected void editMaterialGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Material mat = materialService.find(id);
		request.setAttribute("mat", mat);
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/editMaterial.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/EditMaterial", method = RequestMethod.POST)
	protected void editMaterialPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Material mat = materialService.find(id);
		mat.setName(request.getParameter("inputName"));

		materialService.editMaterial(mat);
		response.sendRedirect(response.encodeURL("./IndexMaterial"));
	}

	@RequestMapping(value = "/RemoveMaterial", method = RequestMethod.GET)
	protected void removeMaterialGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idMat"));
		Material mat = materialService.find(id);
		FolderHandler f = new FolderHandler(valueService.getResourcePath());
		f.cleanMatFolder(mat);
		materialService.remove(mat);

		RequestDispatcher rd = null;
		if (request.getParameter("idParent") != null) {
			rd = request.getRequestDispatcher("/Material?idMat="
					+ request.getParameter("idParent"));
		} else {
			rd = request.getRequestDispatcher("/IndexMaterial");
		}

		rd.forward(request, response);
	}

	@RequestMapping(value = "/RemoveMaterial", method = RequestMethod.POST)
	protected void removeMaterialPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
