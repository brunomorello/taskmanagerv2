package br.com.bmo.taskmanager.service;

import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.model.Status;
import br.com.bmo.taskmanager.repository.StatusRepository;

@Service
public class StatusService {

	private final StatusRepository statusRepository;

	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}

	public void save(Status status) {
		statusRepository.save(status);
	}
	
	public Iterable<Status> getAllStatus() {
		return statusRepository.findAll();
	}
	
	public void delete(Status status) {
		statusRepository.delete(status);
	}
	
}
