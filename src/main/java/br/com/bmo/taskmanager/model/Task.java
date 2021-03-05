package br.com.bmo.taskmanager.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;
	private LocalDateTime dueDate;
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", dueDate=" + dueDate + ", owner=" + owner + ", status=" + status + ", category="
				+ category + "]";
	}
	
}
