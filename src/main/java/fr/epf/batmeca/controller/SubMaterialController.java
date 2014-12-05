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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.MaterialAttribute;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IMaterialService;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.IUserService;

@Controller
public class SubMaterialController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IMaterialService materialService;
	@Autowired
	private ITypeMaterialAttributService typeMaterialAttributService;

	@RequestMapping(value = "/AddSubMaterial", method = RequestMethod.GET)
	protected void addSubMaterialGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// récupération de la liste des materiaux
		List<Material> materials;
		materials = materialService.findAll();
		// récupération de la liste des attributs
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();

		request.setAttribute("mats", materials);
		request.setAttribute("matAttrs", listAttr);

		request.setAttribute("idParent", request.getParameter("idParent"));
		RequestDispatcher rd = request.getRequestDispatcher(response
				.encodeURL("/WEB-INF/addSubMaterial.jsp"));
		rd.forward(request, response);
	}

	@RequestMapping(value = "/AddSubMaterial", method = RequestMethod.POST)
	protected void addSubMaterialPost(HttpServletRequest request,
			HttpServletResponse response, Principal principal) throws ServletException, IOException {
		// récuperation des champs du formulaire
		String name = request.getParameter("inputName");
		System.out.println("NAME  = " + name);
		Material mat = new Material();
		mat.setName(name);
		String parent = request.getParameter("inputMaterialParent");

		Material matParent = materialService.find(Integer.parseInt(parent));
		mat.setMaterialParent(matParent);
		// Récuperation de la liste des attribus
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();
		mat.setMatAttrs(new HashSet<MaterialAttribute>());

		// Création des attributs du sous matériaux
		for (TypeMaterialAttribute tMatAttr : listAttr) {
			MaterialAttribute matAttr = new MaterialAttribute();
			String nameAttr = request
					.getParameter("input" + tMatAttr.getName());
			if (nameAttr.compareTo("") != 0) {
				matAttr.setValue(nameAttr);
				matAttr.setTypeMatAttr(tMatAttr);
				matAttr.setMaterial(mat);
				mat.addMaterialAttribute(matAttr);
			}
		}

		// Association du sous matériaux à l'utilisateur courant
		User user = userService.getUser(principal.getName());
		mat.setUser(user);
		materialService.addMaterial(mat);

		response.sendRedirect(response.encodeURL("./Material?idMat="
				+ mat.getId()));
	}
}
