package fr.epf.batmeca.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.ITypeUserService;
import fr.epf.batmeca.service.IUserService;

@Controller
public class ProfileController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITypeUserService typeUserService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	protected String userGet(ModelMap model, Principal principal) {
		User user = userService.getUser(principal.getName());

		model.addAttribute("user", user);

		return "profile";
	}

	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	protected String editUserGet(ModelMap model, Principal principal) {
		User user = userService.getUser(principal.getName());

		model.addAttribute("user", user);
		model.addAttribute("types", typeUserService.getTypes());

		return "editUser";
	}

	@RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
	protected String editUserPost(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "newpassword") String newPassword,
			@RequestParam(value = "newpasswordconfirm") String newPasswordConfirm,
			Principal principal) {

		User user = userService.getUser(principal.getName());
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);

		// if user wants to change his password
		if (!password.isEmpty() && !newPassword.isEmpty()
				&& newPassword.equals(newPasswordConfirm)) {
			user.setPassword(newPassword);
		}

		userService.editUser(user);

		return "redirect:/profile";
	}
}
