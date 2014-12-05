package fr.epf.batmeca.controller;

import java.io.IOException;
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
	private ITypeMaterialAttributService typeMatAttrService;
	@Autowired
	private ITestService testService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ValueServiceImpl valueService;

	@RequestMapping(value = "/Material", method = RequestMethod.GET)
	protected String materialGet(@RequestParam("idMat") String id,
			ModelMap model) {
		Material mat = materialService.find(Integer.parseInt(id));
		List<Material> childs = materialService.findByParent(mat);

		model.addAttribute("material", mat);
		model.addAttribute("childs", childs);

		return "material";
	}

	@RequestMapping(value = "/IndexMaterial", method = RequestMethod.GET)
	protected String indexMaterialGet(ModelMap model, Principal principal) {
		User user = userService.getUser(principal.getName());
		List<Material> list;

		if (user.getType().getId() == 1) {
			list = materialService.findAllNoParent();
		} else {
			list = materialService.findByUser(user);
		}

		model.addAttribute("materials", list);

		return "indexMaterial";
	}

	@RequestMapping(value = "/addMaterial", method = RequestMethod.GET)
	protected String addMaterialGet(ModelMap model) {
		List<Material> materials = materialService.findAll();
		List<TypeMaterialAttribute> listAttr = typeMatAttrService.findAll();

		model.addAttribute("mats", materials);
		model.addAttribute("matAttrs", listAttr);

		return "addMaterial";
	}

	@RequestMapping(value = "/addMaterial", method = RequestMethod.POST)
	protected String addMaterialPost(
			@RequestParam(value = "inputName") String name,
			@RequestParam(value = "inputMaterialParent") String parent,
			HttpServletRequest request, Principal principal) {

		Material mat = new Material();
		mat.setName(name);
		mat.setMatAttrs(new HashSet<MaterialAttribute>());

		List<TypeMaterialAttribute> listAttr = typeMatAttrService.findAll();
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

		User user = userService.getUser(principal.getName());
		mat.setUser(user);

		materialService.addMaterial(mat);

		return "redirect:/IndexMaterial";
	}

	@RequestMapping(value = "/EditMaterial", method = RequestMethod.GET)
	protected String editMaterialGet(
			@RequestParam(value = "id") String idValue, ModelMap model) {

		int id = Integer.parseInt(idValue);
		Material mat = materialService.find(id);

		model.addAttribute("mat", mat);

		return "editMaterial";
	}

	@RequestMapping(value = "/EditMaterial", method = RequestMethod.POST)
	protected String editMaterialPost(
			@RequestParam(value = "id") String idValue,
			@RequestParam(value = "inputName") String inputName, ModelMap model) {

		int id = Integer.parseInt(idValue);
		Material mat = materialService.find(id);

		mat.setName(inputName);
		materialService.editMaterial(mat);

		return "redirect:/IndexMaterial";
	}

	@RequestMapping(value = "/RemoveMaterial", method = RequestMethod.GET)
	protected String removeMaterialGet(
			@RequestParam(value = "idMat") String idMat,
			@RequestParam(value = "idParent", required = false) String idParent,
			ModelMap model) throws IOException {

		int id = Integer.parseInt(idMat);
		Material mat = materialService.find(id);
		FolderHandler f = new FolderHandler(valueService.getResourcePath());

		f.cleanMatFolder(mat);
		materialService.remove(mat);

		if (idParent != null) {
			return "redirect:/Material?idMat=" + idParent;
		} else {
			return "redirect:/IndexMaterial";
		}
	}
}
