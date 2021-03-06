package br.com.bmo.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@Controller
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("task/form");
		Iterable<Category> categories = categoryRepository.findAll();
		mv.addObject("categories", categories);
		return mv;
	}
	
	@GetMapping("new")
	public ModelAndView newTask(RequestNewTask request) {
		ModelAndView mv = new ModelAndView("home");
		request.toTask();
		return mv;
	}
}
