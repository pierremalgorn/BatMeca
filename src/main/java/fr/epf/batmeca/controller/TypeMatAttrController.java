package fr.epf.batmeca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.service.ITypeMaterialAttributService;

@Controller
public class TypeMatAttrController {

	@Autowired
	private ITypeMaterialAttributService typeService;

	@RequestMapping(value = "/AddTypeAttrMat", method = RequestMethod.GET)
	protected String addTypeAttrMatGet() {
		return "addTypeAttrMat";
	}

	@RequestMapping(value = "/AddTypeAttrMat", method = RequestMethod.POST)
	protected String addTypeAttrMatPost(
			@RequestParam("inputName") String inputName,
			@RequestParam("inputPattern") String inputPattern) {

		TypeMaterialAttribute type = new TypeMaterialAttribute();
		type.setName(inputName);
		type.setPattern(inputPattern);

		typeService.add(type);

		return "redirect:/Config";
	}

	@RequestMapping(value = "/RemoveTypeMatAttr", method = RequestMethod.GET)
	protected String removeTypeMatAttrGet(@RequestParam("id") String id) {
		int typeId = Integer.parseInt(id);
		TypeMaterialAttribute type = typeService.find(typeId);

		typeService.remove(type);

		return "redirect:/Config";
	}
}
