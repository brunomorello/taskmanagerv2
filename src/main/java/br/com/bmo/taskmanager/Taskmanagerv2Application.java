package br.com.bmo.taskmanager;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bmo.taskmanager.orm.Category;
import br.com.bmo.taskmanager.orm.Status;
import br.com.bmo.taskmanager.orm.Task;
import br.com.bmo.taskmanager.orm.User;
import br.com.bmo.taskmanager.service.CategoryService;
import br.com.bmo.taskmanager.service.StatusService;
import br.com.bmo.taskmanager.service.TaskService;
import br.com.bmo.taskmanager.service.UserService;

@SpringBootApplication
public class Taskmanagerv2Application implements CommandLineRunner {
	
	private final CategoryService categoryService;
	private final UserService userService;
	private final StatusService statusService;
	private final TaskService taskService;

	public Taskmanagerv2Application(CategoryService categoryService, UserService userService,
			StatusService statusService, TaskService taskService) {
		this.categoryService = categoryService;
		this.userService = userService;
		this.statusService = statusService;
		this.taskService = taskService;
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
//		
//		User user = new User();
//		user.setFirstName("Bruno");
//		user.setLogin("bmo");
//		user.setPwd("123");
//		userService.save(user);
//		
//		Status backlogStatus = new Status();
//		backlogStatus.setName("Backlog");
//		statusService.save(backlogStatus);
//		
//		Status inprogStatus = new Status();
//		inprogStatus.setName("In Progress");
//		statusService.save(inprogStatus);
//		
//		Status onHoldStatus = new Status();
//		onHoldStatus.setName("On Hold");
//		statusService.save(onHoldStatus);
//		
//		Status doneStatus = new Status();
//		doneStatus.setName("Done");
//		statusService.save(doneStatus);
//		
//		Task task = new Task();
//		task.setDescription("Learn Spring Data");
//		task.setCategory(category);
//		task.setStatus(backlogStatus);
//		task.setOwner(user);
//		
//		LocalDateTime dueDate = LocalDateTime.of(2021, 3, 1, 9, 0);
//		task.setDueDate(dueDate);
//		taskService.save(task);
//		
//		categoryService.showAllCategories();
		
		System.out.println("Finding Users by firstName");
		userService.listUsersFoundByName("Bru%");
		
		System.out.println("Get Tasks Created this Month");
		Iterable<Task> allTasksByMonth = taskService.getAllTasksByMonth(2);
		allTasksByMonth.forEach(System.out::println);
	}

}
