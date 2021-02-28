package br.com.bmo.taskmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bmo.taskmanager.orm.Category;
import br.com.bmo.taskmanager.service.CategoryService;

@SpringBootApplication
public class Taskmanagerv2Application implements CommandLineRunner {
	
	private final CategoryService categoryService;
	
	public Taskmanagerv2Application(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Taskmanagerv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Category category = new Category();
//		category.setName("Study");
//		
//		categoryService.save(category);
		
		Category category2 = new Category();
		category2.setId(3);
		category2.setName("Do the dishes");
		
		categoryService.update(category2);
	}

}
