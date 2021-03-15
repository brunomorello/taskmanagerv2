package br.com.bmo.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {
	
	@PostMapping("/")
	public String create() {
		return "redirect:";
	}
	
}
