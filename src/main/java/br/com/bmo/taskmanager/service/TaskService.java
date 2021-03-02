package br.com.bmo.taskmanager.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.orm.Task;
import br.com.bmo.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
//	private final

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public void save(Task task) {
		taskRepository.save(task);
	}
	
	public Task getTaskById(Integer id) {
		Optional<Task> taskFinderById = taskRepository.findById(id);
		return taskFinderById.get();
	}
	
	public Iterable<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public Iterable<Task> getAllTasksByMonth(Integer month) {
		return taskRepository.findTasksByMonth(month);
	}
	
	public List<Task> getTasksByCategoryName(String name) {
		return taskRepository.findByCategoryName(name);
	}
	
	public List<Task> getTasksByCategoryNameLike(String name) {
		return taskRepository.findByCategoryNameLike(name);
	}
	
	public List<Task> getTasksByDueDateIsOrGreaterThan(String dateTime) {
		LocalDateTime period = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
		return taskRepository.findTaskDueDateIsEqualsOrGreaterThan(period);
	}
}
