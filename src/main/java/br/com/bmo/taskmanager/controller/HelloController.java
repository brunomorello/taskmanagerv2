package br.com.bmo.taskmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request) {
		request.setAttribute("name", "World");
		return "hello";
	}
	
	@GetMapping("/hello2")
	public String hellov2(Model model) {
		model.addAttribute("name", "World!!");
		return "hello";
	}
}
