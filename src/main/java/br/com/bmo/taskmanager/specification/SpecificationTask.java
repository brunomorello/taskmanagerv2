package br.com.bmo.taskmanager.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import br.com.bmo.taskmanager.model.Task;

public class SpecificationTask {

	public static Specification<Task> description(String description) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("description"), "%" + description + "%");
	}
	
	public static Specification<Task> dueDate(LocalDateTime dateTime) {
		return (root, criteriaQuery, criteriaBuilder) ->
		criteriaBuilder.greaterThan(root.get("dueDate"), dateTime);
	}
	
	public static Specification<Task> category(Integer categoryId) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("category"), categoryId);
	}
	
	public static Specification<Task> status(Integer statusId) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("status"), statusId);
	}
	
	public static Specification<Task> owner(Integer ownerId) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("owner"), ownerId);
	}
}
