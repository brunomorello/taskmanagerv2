package br.com.bmo.taskmanager.controller.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmo.taskmanager.controller.dto.TaskDTO;
import br.com.bmo.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskAPIController {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/")
	public List<TaskDTO> getAllTasks() {
		return TaskDTO.toList(taskRepository.findAll());
	}
}
