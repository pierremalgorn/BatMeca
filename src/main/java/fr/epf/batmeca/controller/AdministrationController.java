package fr.epf.batmeca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.ITypeUserService;
import fr.epf.batmeca.service.IUserService;

@Controller
public class AdministrationController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITypeUserService typeUserService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	protected String adminGet() {
		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
	protected String listUsersGet(ModelMap model) {
		model.addAttribute("users", userService.findAllUsers());
		return "indexUser";
	}

	@RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
	protected String addUserGet(ModelMap model) {
		model.addAttribute("types", typeUserService.getTypes());
		return "addUser";
	}

	@RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
	protected String addUserPost(@RequestParam(value = "name") String name,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "type") String type) {

		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));

		User user = new User();
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);
		user.setPassword(password);
		user.setType(typeUser);

		userService.addUser(user);

		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = "/admin/user/edit/{id}", method = RequestMethod.GET)
	protected String editUserAdminGet(@PathVariable(value = "id") int id,
			ModelMap model) {

		model.addAttribute("admin", true);
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("types", typeUserService.getTypes());

		return "editUser";
	}

	@RequestMapping(value = "/admin/user/edit/{id}", method = RequestMethod.POST)
	protected String editUserAdminPost(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "newpassword") String newPassword,
			@RequestParam(value = "newpasswordconfirm") String newPasswordConfirm,
			ModelMap model) {

		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));

		User user = userService.getUser(id);
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);
		user.setType(typeUser);

		// if user wants to change his password
		if (!password.isEmpty() && !newPassword.isEmpty()
				&& newPassword.equals(newPasswordConfirm)) {
			user.setPassword(newPassword);
		}

		userService.editUser(user);

		model.addAttribute("event", "edited");

		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = "/admin/user/remove/{id}", method = RequestMethod.GET)
	protected String removeUserGet(@PathVariable(value = "id") int id,
			ModelMap model) {
		User user = userService.getUser(id);
		userService.removeUser(user);

		model.addAttribute("event", "removed");

		return "redirect:/admin/user/list";
	}
}
