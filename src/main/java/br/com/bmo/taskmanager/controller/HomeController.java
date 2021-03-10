package br.com.bmo.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.service.TaskService;

@Controller
public class HomeController {
	
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

	// Initial Example
//	@GetMapping("/home")
//	public String home(Model model) {
//		List<Task> allTasksByCategory = taskService.getTasksByCategoryNameLike("%study%");
//		model.addAttribute("tasks", allTasksByCategory);
//		return "home";
//	}
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/home")
	public ModelAndView home() {
		List<Task> allTasksByCategory = taskService.getTasksByCategoryNameLike("%study%");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("tasks", allTasksByCategory);
		return mv;
	}
	
}
