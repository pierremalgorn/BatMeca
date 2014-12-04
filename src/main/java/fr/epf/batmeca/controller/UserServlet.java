package fr.epf.batmeca.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IUserService;

@Controller
@RequestMapping("/User")
public class UserServlet {

	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap model, Principal principal) {
		User user = userService.getUser(principal.getName());
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
