package fr.epf.batmeca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handle user session
 */
@Controller
public class LoginServlet {

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
//		return "redirect:/list"; XXX
	}
}
