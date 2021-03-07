package br.com.bmo.taskmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bmo.taskmanager.dto.RequestCategory;
import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@Controller
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("category/list");
		Iterable<Category> categoriesList = categoryRepository.findAll();
		mv.addObject("categoriesList", categoriesList);
		return mv;
	}
	
	@GetMapping("form")
	public String form(RequestCategory request) {
		return "category/form";
	}
	
	@PostMapping("create")
	public String create(@Valid RequestCategory request, BindingResult result) {
		
		if (result.hasErrors()) {
			return "category/form";
		}
		
		Category category = request.parse();
		categoryRepository.save(category);
		return "redirect:/category/list";
	}
}
