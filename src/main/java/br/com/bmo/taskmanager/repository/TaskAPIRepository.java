package br.com.bmo.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bmo.taskmanager.model.Task;

public interface TaskAPIRepository extends JpaRepository<Task, Integer>{
	Page<Task> findByOwner_Username(String username, Pageable pageable);
}
