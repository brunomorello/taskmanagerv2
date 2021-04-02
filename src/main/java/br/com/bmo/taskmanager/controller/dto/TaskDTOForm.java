package br.com.bmo.taskmanager.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.model.Status;
import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.model.User;
import br.com.bmo.taskmanager.repository.CategoryRepository;
import br.com.bmo.taskmanager.repository.StatusRepository;
import br.com.bmo.taskmanager.repository.TaskAPIRepository;
import br.com.bmo.taskmanager.repository.UserRepository;

public class TaskDTOForm {

	@NotEmpty @NotNull
	private String description;
	private String details;
	@NotEmpty @NotNull
	private String category;
	@NotEmpty @NotNull
	private String status;
	@NotEmpty @NotNull
	private String owner;
	private String dueDate;
	
	public TaskDTOForm() {
		
	}
	
	public TaskDTOForm(String description, String details, String category, String status, String owner, String dueDate) {
		this.description = description;
		this.details = details;
		this.category = category;
		this.status = status;
		this.owner = owner;
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public String getDetails() {
		return details;
	}

	public String getCategory() {
		return category;
	}

	public String getStatus() {
		return status;
	}

	public String getOwner() {
		return owner;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public Task parse(UserRepository userRepository, StatusRepository statusRepository, CategoryRepository categoryRepository) {
		Optional<User> userOpt = userRepository.findByUsername(getOwner());
		User userObj = null; 
		if (userOpt.isPresent())
			userObj = userOpt.get();
		Status statusObj = statusRepository.findByName(getStatus());
		Category categoryObj = categoryRepository.findByName(getCategory());
		return new Task(
				getDescription(), 
				LocalDateTime.parse(getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME), 
				userObj, 
				statusObj, 
				categoryObj, 
				getDetails());
	}

	public Task update(Integer id, TaskAPIRepository taskAPIRepository, StatusRepository statusRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		Task task = taskAPIRepository.getOne(id);
		Optional<User> userOpt = userRepository.findByUsername(getOwner());
		User userObj = userOpt.isPresent() ? userOpt.get() : null; 
		Status statusObj = statusRepository.findByName(getStatus());
		Category categoryObj = categoryRepository.findByName(getCategory());
		
		task.setDescription(getDescription());
		task.setDetails(getDetails());
		if (getDueDate() != null)
			task.setDueDate(LocalDateTime.parse(getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		task.setOwner(userObj);
		task.setStatus(statusObj);
		task.setCategory(categoryObj);
		task.setUpdatedAt(LocalDateTime.now());
		return task;
	}
	
}
