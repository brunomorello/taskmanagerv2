package br.com.bmo.taskmanager.controller.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

public class CategoryDTOForm {

	@NotNull @NotBlank
	private String name;
	
	public CategoryDTOForm(Category category) {
		this.name = category.getName();
	}
	
	public CategoryDTOForm() {
	}

	public String getName() {
		return name;
	}
	
	public Category parse(CategoryRepository categoryRepository) {
		Category category = new Category();
		category.setName(getName());
		return categoryRepository.save(category);
	}
	
	public Category parse(CategoryRepository categoryRepository, Integer id) {
		Optional<Category> categoryFound = categoryRepository.findById(id);
		if (categoryFound.isPresent()) {
			Category category = categoryFound.get();
			category.setName(getName());
			return categoryRepository.save(category);
		}
		return null;
	}
	
}
