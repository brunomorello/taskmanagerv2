package br.com.bmo.taskmanager.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bmo.taskmanager.dto.RequestCategory;
import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@Controller
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("category/list");
		Iterable<Category> categoriesList = categoryRepository.findAll();
		mv.addObject("categoriesList", categoriesList);
		return mv;
	}
	
	@GetMapping("/form/{id}")
	public String formEditCategory(@PathVariable("id") String id, RequestCategory request, Model model) {
		Optional<Category> category = categoryRepository.findById(Integer.valueOf(id));
		if (category.isPresent())
			model.addAttribute("category", category.get());
		return "category/form";
	}
	
	@GetMapping("/form")
	public String form(RequestCategory request, Model model) {
		return "category/form";
	}
	
	@PostMapping("/")
	public String saveCategory(@Valid RequestCategory request, BindingResult result) {
		if (result.hasErrors()) {
			return "category/form";
		}
		
		Category category = request.parse();
		categoryRepository.save(category);
		return "redirect:/categories/";
	}
	
	@PostMapping("/delete")
	public String deleteCategory(RequestCategory request, Model model) {
		Optional<Category> category = categoryRepository.findById(Integer.valueOf(request.getCategoryId()));
		if (category.isPresent()) {
			try {
				categoryRepository.deleteById(category.get().getId());				
			} catch (Exception e) {
				model.addAttribute("deleteError", e.getMessage());
				return "category/list";
			}
		}
		return "redirect:/categories/";
	}
	
}
