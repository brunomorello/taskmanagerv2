package br.com.bmo.taskmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bmo.taskmanager.dto.RequestNewUser;
import br.com.bmo.taskmanager.model.User;
import br.com.bmo.taskmanager.repository.UserRepository;

@Controller
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public ModelAndView listUsers() {
		ModelAndView mv = new ModelAndView("users/list");
		Iterable<User> allUsersList = userRepository.findAll();
		mv.addObject("allUsersList", allUsersList);
		return mv;
	}
	
	@PostMapping("/")
	public String newUser(@Valid RequestNewUser request, BindingResult result) {
		if (result.hasErrors()) {
			return "users/form";
		}
		
		User user = request.toUser();
		userRepository.save(user);
		return "redirect:/users/";
	}
	
	@GetMapping("/form")
	public String userForm(RequestNewUser request, Model model) {
		return "users/form";
	}
	
}
