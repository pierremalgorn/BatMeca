package fr.epf.batmeca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epf.batmeca.service.IUserService;

/**
 * Handle user session
 */
@Controller
public class LoginServlet {

	@Autowired
	private IUserService userService;

//	@RequestMapping(method = RequestMethod.GET)
//	protected ModelAndView doGet() {
//		return new ModelAndView("login");
////		HttpSession session = request.getSession();
////
////		if (session != null) {
////			session.removeAttribute("sessionUser"); TODO disconnect user
////		}
////
////		RequestDispatcher rd = request.getRequestDispatcher(response
////				.encodeURL("/WEB-INF/login.jsp"));
////		rd.forward(request, response);
//	}

//	@RequestMapping(method = RequestMethod.POST)
//	protected ModelAndView doPost(@RequestParam String login, @RequestParam String password) {
//		User user = userService.getUserByLoginMdp(login, password);
//		ModelAndView view = new ModelAndView();
//
//		if (user != null) {
////			HttpSession session = request.getSession();
////			session.setAttribute("sessionUser", user);
////			response.sendRedirect(response.encodeURL("./IndexMaterial"));
//			view.addObject("sessionUser", user);
//			view.setViewName("IndexMaterial");
//		} else {
//			view.addObject("error", "yes");
//			view.setViewName("login");
//		}
//
//		return view;
	// }

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
//		return "redirect:/list"; XXX
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}
}
