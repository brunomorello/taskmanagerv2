package br.com.bmo.taskmanager.controller.dto;

import org.springframework.data.domain.Page;

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
	
	public TaskDTO(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();
		this.details = task.getDetails();
		this.status = task.getStatus();
		this.category = task.getCategory();
		this.owner = task.getOwner().getUsername();
		this.createdAt = task.getCreatedAt().toString();
		this.updatedAt = task.getUpdatedAt().toString();
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

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public static Page<TaskDTO> toList(Page<Task> allTasks) {
//		List<TaskDTO> parsedTasksList = new ArrayList<>();
//		findAll.forEach(task -> {
//			parsedTasksList.add(
//					new TaskDTO(
//						task.getId(), 
//						task.getDescription(), 
//						task.getDetails(), 
//						task.getStatus(), 
//						task.getCategory(), 
//						task.getOwner().getUsername(), 
//						task.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), 
//						task.getUpdatedAt() != null ? task.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "")
//					);
//		});
		// Trying java 8 - no success
//		List<Task> result = StreamSupport.stream(findAll.spliterator(), false)
//				.collect(Collectors.toList());
//		return result.stream().map(TaskDTO::new).collect(Collectors.toList());
		return allTasks.map(TaskDTO::new);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDTO other = (TaskDTO) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", description=" + description + ", details=" + details + ", status=" + status
				+ ", category=" + category + ", owner=" + owner + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
	
}
