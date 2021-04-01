package br.com.bmo.taskmanager.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bmo.taskmanager.dto.RequestNewTask;
import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.model.Status;
import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.model.User;
import br.com.bmo.taskmanager.repository.CategoryRepository;
import br.com.bmo.taskmanager.repository.StatusRepository;
import br.com.bmo.taskmanager.repository.TaskRepository;
import br.com.bmo.taskmanager.repository.UserRepository;

@Controller
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository; 
	@GetMapping("form")
	
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("task/form");
		Iterable<Category> categories = categoryRepository.findAll();
		mv.addObject("categories", categories);
		Iterable<User> users = userRepository.findAll();
		mv.addObject("users", users);
		Iterable<Status> statusList = statusRepository.findAll();
		mv.addObject("statusList", statusList);
		return mv;
	}
	
	@PostMapping("new")
	public String newTask(@Valid RequestNewTask request, BindingResult result) {
		if (result.hasErrors()) {
			return "/task/form";
		}
		
		Task task = request.toTask();
		taskRepository.save(task);
		return "redirect:/home";
	}
	
	@GetMapping("/{status}")
	public String taskStatus(@PathVariable("status") String status, Model model, Principal principal) {
		Optional<User> userOpt = userRepository.findByUsername(principal.getName());
		User user = userOpt.get();
		List<Task> tasks = taskRepository.findByStatusAndOwner(statusRepository.findByName(status), user);
		model.addAttribute("tasks", tasks);
		model.addAttribute("status", status);
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
