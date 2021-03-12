package br.com.bmo.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vuePortal")
public class VuePortalController {

	@GetMapping
	public String listCategories() {
		return "vuePortal/home";
	}
}
