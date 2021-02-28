package br.com.bmo.taskmanager.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.orm.Task;
import br.com.bmo.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

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
}
