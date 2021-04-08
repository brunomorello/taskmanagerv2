package br.com.bmo.taskmanager;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.model.Status;
import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.model.TaskProjection;
import br.com.bmo.taskmanager.model.User;
import br.com.bmo.taskmanager.service.CategoryService;
import br.com.bmo.taskmanager.service.StatusService;
import br.com.bmo.taskmanager.service.TaskReportService;
import br.com.bmo.taskmanager.service.TaskService;
import br.com.bmo.taskmanager.service.UserService;

@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport
public class Taskmanagerv2Application implements CommandLineRunner {
//public class Taskmanagerv2Application extends SpringBootServletInitializer {
	
	// run mvn clean package (generate build)
//	@Override
//		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//			return builder.sources(Taskmanagerv2Application.class);
//		}
	
//	private final CategoryService categoryService;
//	private final UserService userService;
//	private final StatusService statusService;
//	private final TaskService taskService;
//	private final TaskReportService taskReportService;
//
//	public Taskmanagerv2Application(CategoryService categoryService, UserService userService,
//			StatusService statusService, TaskService taskService, TaskReportService taskReportService) {
//		this.categoryService = categoryService;
//		this.userService = userService;
//		this.statusService = statusService;
//		this.taskService = taskService;
//		this.taskReportService = taskReportService;
//	}

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
		
//		System.out.println("Finding Users by firstName");
//		userService.listUsersFoundByName("Bru%");
//		
//		System.out.println("Get Tasks Created this Month");
//		Iterable<Task> allTasksByMonth = taskService.getAllTasksByMonth(2);
//		allTasksByMonth.forEach(System.out::println);
//		
//		System.out.println("Select Tasks by Category.name=Studyi");
//		List<Task> allTasksByCategory = taskService.getTasksByCategoryName("Studyi");
//		allTasksByCategory.forEach(System.out::println);
//		
//		System.out.println("Select Tasks by Category.name LIKE %study%");
//		allTasksByCategory = taskService.getTasksByCategoryNameLike("%study%");
//		allTasksByCategory.forEach(System.out::println);
//		
//		System.out.println("Select Tasks based on Due Date using Native Query");
//		List<Task> tasksList = taskService.getTasksByDueDateIsOrGreaterThan("2021-03-01T00:00:00");
//		tasksList.forEach(System.out::println);
//		
//		System.out.println("Select All Tasks at page 0");
//		Iterable<Task> allTasks = taskService.getAllTasks();
//		allTasks.forEach(System.out::println);
//		
//		System.out.println("Select All Tasks - Shows only Description and Status");
//		List<TaskProjection> tasksByDescrAndStatus = taskService.getTaskByDescriptionAndStaus();
//		tasksByDescrAndStatus.forEach(task -> {
//			System.out.println("Task Description: " + task.getDescription() + " - " + task.getStatusName());
//		});
//		
//		System.out.println("Testing Task Report using Specification");
//		taskReportService.execute(null, null, 5, null, null);
	}

}
