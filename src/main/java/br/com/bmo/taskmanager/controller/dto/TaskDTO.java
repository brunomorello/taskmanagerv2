package br.com.bmo.taskmanager.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
	private String createdAt;
	private String updatedAt;
	
	public TaskDTO(Integer id, String description, String details, Status status, Category category, String owner,
			String createdAt, String updatedAt) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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
						task.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), 
						(task.getUpdatedAt() != null) ? task.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "")
					);
		});
		return parsedTasksList;
	}

}
