package br.com.bmo.taskmanager.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.taskmanager.controller.dto.CategoryDTOForm;
import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPIController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/")
	@Cacheable(value = "listCategories")
	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	@PostMapping("/")
	@Transactional
	@CacheEvict(value = "listCategories", allEntries = true)
	public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTOForm form, UriComponentsBuilder uriBuilder) {
	
		Category category = form.parse(categoryRepository);
		
		URI uri = uriBuilder.path("/api/categories/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).body(category);
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listCategories", allEntries = true)
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTOForm form) {
		Category category = form.parse(categoryRepository, id);
		
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listCategories", allEntries = true)
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		Optional<Category> categoryOpt = categoryRepository.findById(id);
		if (categoryOpt.isPresent()) {
			categoryRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
		Optional<Category> categoryOpt = categoryRepository.findById(id);
		if (categoryOpt.isPresent()) {
			return ResponseEntity.ok(categoryOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
}