package fr.epf.batmeca.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.ITypeMaterialAttributService;
import fr.epf.batmeca.service.ITypeTestAttributService;

@Controller
public class ConfigController {

	@Autowired
	private ITypeMaterialAttributService typeMatAttrService;
	@Autowired
	private ITypeTestAttributService typeTestAttrService;

	@RequestMapping(value = "/Config", method = RequestMethod.GET)
	protected String configGet(ModelMap model) {
		List<TypeMaterialAttribute> typesMat = typeMatAttrService.findAll();
		List<TypeTestAttribute> typesTest = typeTestAttrService.findAll();

		model.addAttribute("typeMats", typesMat);
		model.addAttribute("typeTests", typesTest);

		return "config";
	}

	@RequestMapping(value = "/ConfigGenerator", method = RequestMethod.GET)
	protected String configGeneratorGet() {
		return "configGenerator";
	}

	@RequestMapping(value = "/ConfigGenerator", method = RequestMethod.POST)
	protected String configGeneratorPost(ModelMap model) {
		Iterator<String> params = model.keySet().iterator();

		while (params.hasNext()) {
			String string = params.next();
			// String val = request.getParameter(string);

			System.out.println("Name Field = " + string);
			// System.out.println("Val Filed = "+val);
		}

		// TODO finish this function

		return "configGenerator";
	}
}
