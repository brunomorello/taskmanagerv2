package br.com.bmo.taskmanager.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Cacheable(value = "tasksList")
	public Page<TaskDTO> getAllTasks(@RequestParam(required = false) String username,
			@RequestParam int page, @RequestParam int qnt, @RequestParam String orderBy) {
		
		Pageable pagination = PageRequest.of(page, qnt, Sort.by(Sort.Direction.ASC, orderBy));
		
		if (username == null)
			return TaskDTO.toList(taskAPIRepository.findAll(pagination));
		else
			return TaskDTO.toList(taskAPIRepository.findByOwner_Username(username, pagination));
	}
	
	@PostMapping("/")
	@Transactional
	@CacheEvict(value = "tasksList", allEntries = true)
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
	public ResponseEntity<TaskDTO> taskDetails(@PathVariable Integer id) {
		Optional<Task> taskOpt = taskRepository.findById(id);
		if (taskOpt.isPresent()) {
			Task task = taskOpt.get();
			return ResponseEntity.ok(
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
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "tasksList", allEntries = true)
	public ResponseEntity<TaskDTO> updateTask(@PathVariable Integer id, @RequestBody @Valid TaskDTOForm form) {
		Optional<Task> taskFound = taskAPIRepository.findById(id);
		if (taskFound.isPresent()) {
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
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
		Optional<Task> taskFound = taskAPIRepository.findById(id);
		if (taskFound.isPresent()) {
			taskAPIRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
