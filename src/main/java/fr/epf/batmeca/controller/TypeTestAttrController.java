package fr.epf.batmeca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.ITypeTestAttributService;

@Controller
public class TypeTestAttrController {

	@Autowired
	private ITypeTestAttributService typeService;

	@RequestMapping(value = "/AddTypeTestAttr", method = RequestMethod.GET)
	protected String addTypeTestAttrGet() {
		return "addTypeTestAttr";
	}

	@RequestMapping(value = "/AddTypeTestAttr", method = RequestMethod.POST)
	protected String addTypeTestAttrPost(
			@RequestParam(value = "inputName") String inputName,
			@RequestParam(value = "inputPattern") String inputPattern) {

		TypeTestAttribute type = new TypeTestAttribute();
		type.setName(inputName);
		type.setPattern(inputPattern);

		typeService.add(type);

		return "redirect:/Config";
	}

	@RequestMapping(value = "/RemoveTypeTestAttr", method = RequestMethod.GET)
	protected String removeTypeTestAttrGet(@RequestParam(value = "id") String id) {
		int typeId = Integer.parseInt(id);
		TypeTestAttribute type = typeService.find(typeId);

		typeService.remove(type);

		return "redirect:/Config";
	}
}
