package br.com.bmo.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.service.CategoryService;
import br.com.bmo.taskmanager.service.StatusService;
import br.com.bmo.taskmanager.service.TaskReportService;
import br.com.bmo.taskmanager.service.TaskService;
import br.com.bmo.taskmanager.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskReportService taskReportService;
	
//	private final CategoryService categoryService;
//	private final UserService userService;
//	private final StatusService statusService;
//	private final TaskService taskService;
//	private final TaskReportService taskReportService;
	
//	public HomeController(CategoryService categoryService, UserService userService, StatusService statusService,
//			TaskService taskService, TaskReportService taskReportService) {
//		this.categoryService = categoryService;
//		this.userService = userService;
//		this.statusService = statusService;
//		this.taskService = taskService;
//		this.taskReportService = taskReportService;
//	}

	@GetMapping("/home")
	public String home(Model model) {
		List<Task> allTasksByCategory = taskService.getTasksByCategoryNameLike("%study%");
		model.addAttribute("tasks", allTasksByCategory);
		return "home";
	}
}
