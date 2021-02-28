package br.com.bmo.taskmanager.service;

import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.orm.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void save(Category category) {
		categoryRepository.save(category);
	}
	
	public void showAllCategories() {
		Iterable<Category> categoriesList = categoryRepository.findAll();
		categoriesList.forEach(category -> System.out.println(category));
	}
	
	public void delete(Category category) {
		categoryRepository.deleteById(category.getId());
	}
	
}
