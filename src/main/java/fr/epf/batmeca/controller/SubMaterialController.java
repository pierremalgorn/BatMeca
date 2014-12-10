package fr.epf.batmeca.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	private ITypeMaterialAttributService typeMatAttrService;

	@RequestMapping(value = "/AddSubMaterial", method = RequestMethod.GET)
	protected String addSubMaterialGet(
			@RequestParam(value = "idParent") String idParent, ModelMap model) {

		List<Material> materials = materialService.findAll();
		List<TypeMaterialAttribute> listAttr = typeMatAttrService.findAll();

		model.addAttribute("mats", materials);
		model.addAttribute("matAttrs", listAttr);
		model.addAttribute("idParent", idParent);

		return "addSubMaterial";
	}

	@RequestMapping(value = "/AddSubMaterial", method = RequestMethod.POST)
	protected String addSubMaterialPost(
			@RequestParam(value = "inputName") String name,
			@RequestParam(value = "inputMaterialParent") String parent,
			HttpServletRequest request, Principal principal) {

		Material mat = new Material();
		mat.setName(name);

		Material matParent = materialService.find(Integer.parseInt(parent));
		mat.setMaterialParent(matParent);

		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMatAttrService.findAll();
		mat.setMatAttrs(new HashSet<MaterialAttribute>());

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

		User user = userService.getUser(principal.getName());
		mat.setUser(user);
		materialService.addMaterial(mat);

		return "redirect:/Material?idMat=" + mat.getId();
	}
}
