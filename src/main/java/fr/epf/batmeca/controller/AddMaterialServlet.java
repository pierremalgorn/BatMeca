package fr.epf.batmeca.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.MaterialAttribute;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IMaterialService;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.IUserService;

/**
 * Servlet implementation class addMaterialServlet
 */
@Controller
@RequestMapping("/addMaterial")
public class AddMaterialServlet {

	@Autowired
	private IMaterialService materialService;
	@Autowired
	private ITypeMaterialAttributService typeMaterialAttributService;
	@Autowired
	private IUserService userService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@RequestParam(value = "inputName") String name,
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
			String nameAttr = request.getParameter("input" + tMatAttr.getName());
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
}
