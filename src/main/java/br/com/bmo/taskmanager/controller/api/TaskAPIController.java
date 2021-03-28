package br.com.bmo.taskmanager.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.taskmanager.controller.dto.TaskDTO;
import br.com.bmo.taskmanager.controller.dto.TaskDTOForm;
import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.repository.CategoryRepository;
import br.com.bmo.taskmanager.repository.StatusRepository;
import br.com.bmo.taskmanager.repository.TaskAPIRepository;
import br.com.bmo.taskmanager.repository.TaskRepository;
import br.com.bmo.taskmanager.repository.UserRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskAPIController {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskAPIRepository taskAPIRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public List<TaskDTO> getAllTasks(String username) {
		if (username == null)
			return TaskDTO.toList(taskRepository.findAll(Sort.by("createdAt").ascending()));
		else
			return TaskDTO.toList(taskRepository.findByOwner_Username(username));
	}
	
	@PostMapping("/")
	public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTOForm form, UriComponentsBuilder uriBuilder) {
		
		Task task = form.parse(userRepository, statusRepository, categoryRepository);
		taskRepository.save(task);
		
		URI uri = uriBuilder.path("/api/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(
				new TaskDTO(
						task.getId(), 
						task.getDescription(), 
						task.getDetails(), 
						task.getStatus(), 
						task.getCategory(), 
						task.getOwner().getUsername(), 
						task.getCreatedAt().toString(), 
						task.getUpdatedAt() != null ? task.getUpdatedAt().toString() : ""
					)
				);
	}
	
	@GetMapping("/{id}")
	public TaskDTO taskDetails(@PathVariable Integer id) {
		Optional<Task> taskOpt = taskRepository.findById(id);
		Task task = taskOpt.get();
		return new TaskDTO(
				task.getId(), 
				task.getDescription(), 
				task.getDetails(), 
				task.getStatus(), 
				task.getCategory(), 
				task.getOwner().getUsername(), 
				task.getCreatedAt().toString(), 
				task.getUpdatedAt() != null ? task.getUpdatedAt().toString() : ""
			);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskDTO> updateTask(@PathVariable Integer id, @RequestBody @Valid TaskDTOForm form) {
		Task taskUpdated = form.update(id, taskAPIRepository, statusRepository, categoryRepository, userRepository);
		return ResponseEntity.ok(
				new TaskDTO(
						taskUpdated.getId(),
						taskUpdated.getDescription(), 
						taskUpdated.getDetails(),
						taskUpdated.getStatus(),
						taskUpdated.getCategory(),
						taskUpdated.getOwner().getUsername(),
						taskUpdated.getCreatedAt().toString(),
						taskUpdated.getUpdatedAt().toString()
					));
	}
}
