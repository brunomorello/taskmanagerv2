package br.com.bmo.taskmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bmo.taskmanager.orm.Category;
import br.com.bmo.taskmanager.repository.CategoryRepository;

@SpringBootApplication
public class Taskmanagerv2Application implements CommandLineRunner {

	private final CategoryRepository repository;
	
	public Taskmanagerv2Application(CategoryRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Taskmanagerv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Housekeeping");
		
		repository.save(category);
	}

}
