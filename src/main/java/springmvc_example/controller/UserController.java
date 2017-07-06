package springmvc_example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springmvc_example.model.User;
import springmvc_example.service.UserService;

@Controller

@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "user/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("user/user_page");

		List<User> list = userService.listAllUser();
		mv.addObject("listUser", list);

		return mv;
	}

	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("user/user_form");

		User user = new User();
		mv.addObject("userForm", user);

		return mv;
	}

	@RequestMapping(value = "user/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("user/user_form");

		User user = userService.findById(id);
		mv.addObject("userForm", user);

		return mv;
	}

	@RequestMapping(value = "user/save/{id}", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("userform") User user) {

		if (user != null && user.getId() != null) {
			userService.updateUser(user);
		} else {
			userService.addUser(user);
		}

		return new ModelAndView("redirect:/list");
	}

	@RequestMapping(value = "user/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ModelAndView("redirect:/list");
	}

}
