package br.com.bmo.taskmanager.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.model.Status;
import br.com.bmo.taskmanager.model.Task;

public class TaskDTO {

	private Integer id;
	private String description;
	private String details;
	private Status status;
	private Category category;
	private String owner;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public TaskDTO(Integer id, String description, String details, Status status, Category category, String owner,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.description = description;
		this.details = details;
		this.status = status;
		this.category = category;
		this.owner = owner;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getDetails() {
		return details;
	}

	public Status getStatus() {
		return status;
	}

	public Category getCategory() {
		return category;
	}

	public String getOwner() {
		return owner;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public static List<TaskDTO> toList(Iterable<Task> findAll) {
		List<TaskDTO> parsedTasksList = new ArrayList<>();
		findAll.forEach(task -> {
			parsedTasksList.add(
					new TaskDTO(
						task.getId(), 
						task.getDescription(), 
						task.getDetails(), 
						task.getStatus(), 
						task.getCategory(), 
						task.getOwner().getUsername(), 
						task.getCreatedAt(), 
						task.getUpdatedAt())
					);
		});
		// Trying java 8 - no success
//		List<Task> result = StreamSupport.stream(findAll.spliterator(), false)
//				.collect(Collectors.toList());
//		return result.stream().map(TaskDTO::new).collect(Collectors.toList());
		return parsedTasksList;
	}

}
