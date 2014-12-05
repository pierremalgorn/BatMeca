package fr.epf.batmeca.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.ITypeUserService;
import fr.epf.batmeca.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITypeUserService typeUserService;

	@RequestMapping(value = "/User", method = RequestMethod.GET)
	protected String userGet(ModelMap model, Principal principal) {
		User user = userService.getUser(principal.getName());
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping(value = "/IndexUser", method = RequestMethod.GET)
	protected String indexUserGet(ModelMap model) {
		model.addAttribute("users", userService.findAllUsers());
		return "indexUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	protected String addUserGet(ModelMap model) {
		model.addAttribute("types", typeUserService.getTypes());
		return "addUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	protected String addUserPost(@RequestParam("name") String name,
			@RequestParam("firstName") String firstName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("type") String type) {

		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));

		User user = new User();
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);
		user.setPassword(password);
		user.setType(typeUser);

		userService.addUser(user);

		return "redirect:/IndexUser";
	}

	@RequestMapping(value = "/EditUser", method = RequestMethod.GET)
	protected String editUserGet(@RequestParam("id") String edit, ModelMap model) {
		int id = Integer.parseInt(edit);

		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("types", typeUserService.getTypes());

		return "editUser";
	}

	@RequestMapping(value = "/EditUser", method = RequestMethod.POST)
	protected String editUserPost(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("firstName") String firstName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("type") String type,
			@RequestParam("newpassword") String newPassword,
			@RequestParam("newpasswordconfirm") String newPasswordConfirm) {

		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));

		User user = userService.getUser(Integer.parseInt(id));
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);
		user.setType(typeUser);

		// if user wants to change his password
		if (!password.equals("") && !newPassword.equals("")
				&& newPassword.equals(newPasswordConfirm)) {
			user.setPassword(newPassword);
		} else {
			user.setPassword(user.getPassword());
		}

		userService.editUser(user);

		return "redirect:/User";
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/EditUserAdmin", method = RequestMethod.GET)
	protected String editUserAdminGet(@RequestParam("id") String edit,
			ModelMap model) {
		int id = Integer.parseInt(edit);

		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("types", typeUserService.getTypes());

		return "editUserAdmin";
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/EditUserAdmin", method = RequestMethod.POST)
	protected String editUserAdminPost(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("firstName") String firstName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("type") String type,
			@RequestParam("newpassword") String newPassword,
			@RequestParam("newpasswordconfirm") String newPasswordConfirm) {

		TypeUser typeUser = new TypeUser();
		typeUser.setId(Integer.parseInt(type));

		User user = userService.getUser(Integer.parseInt(id));
		user.setName(name);
		user.setFirstName(firstName);
		user.setEmail(email);
		user.setType(typeUser);

		// if user wants to change his password
		if (!password.equals("") && !newPassword.equals("")
				&& newPassword.equals(newPasswordConfirm)) {
			user.setPassword(newPassword);
		} else {
			user.setPassword(user.getPassword());
		}

		userService.editUser(user);

		return "redirect:/IndexUser";
	}

	@RequestMapping(value = "/RemoveUser", method = RequestMethod.GET)
	protected String removeUserGet(@RequestParam("id") String id, ModelMap model) {
		User user = userService.getUser(Integer.parseInt(id));
		userService.removeUser(user);

		model.addAttribute("event", "userremoved");

		return "redirect:/IndexUser";
	}
}
