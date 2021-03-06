package br.com.bmo.taskmanager.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanager.model.Category;
import br.com.bmo.taskmanager.model.Status;
import br.com.bmo.taskmanager.model.Task;
import br.com.bmo.taskmanager.model.User;

public class RequestNewTask {

	@NotBlank
	private String taskDesc;
	@NotBlank
	private String taskCategory;
	private String taskDueDate;
	private String taskDetails;
	@NotBlank
	private String taskOwner;
	@NotBlank
	private String taskStatus;
	
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getTaskCategory() {
		return taskCategory;
	}
	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}
	public String getTaskDueDate() {
		return taskDueDate;
	}
	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}
	public String getTaskDetails() {
		return taskDetails;
	}
	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}
	public String getTaskOwner() {
		return taskOwner;
	}
	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}
	
	public Task toTask() {
		Task task = new Task();
		task.setDescription(getTaskDesc());
		
		Category category = new Category();
		category.setId(Integer.valueOf(getTaskCategory()));
		task.setCategory(category);
		
		User user = new User();
		user.setId(Integer.valueOf(getTaskOwner()));
		task.setOwner(user);
		
		Status status = new Status();
		status.setId(Integer.valueOf(getTaskStatus()));
		task.setStatus(status);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dueDate = LocalDateTime.parse(taskDueDate, formatter);
		task.setDueDate(dueDate);
		
		task.setDetails(getTaskDetails());
		return task;
	}
	
	
}
