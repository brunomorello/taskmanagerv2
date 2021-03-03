package br.com.bmo.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.orm.Task;
import br.com.bmo.taskmanager.repository.TaskRepository;
import br.com.bmo.taskmanager.specification.SpecificationTask;

@Service
public class TaskReportService {

	private final TaskRepository taskRepository;

	public TaskReportService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public void execute(String description, Integer categoryId, Integer ownerId, 
			Integer statusId, LocalDateTime dateTime) {
		List<Task> tasksList = taskRepository.findAll(Specification
				.where(
						SpecificationTask.description(description))
						.or(SpecificationTask.category(categoryId))
						.or(SpecificationTask.owner(ownerId))
						.or(SpecificationTask.status(statusId))
						.or(SpecificationTask.dueDate(dateTime))
				);
		
		tasksList.forEach(System.out::println);
	}
	
}
