package br.com.bmo.taskmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoriesRest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public Iterable<Category> getOnHold() {
		return categoryRepository.findAll();
	}
}
