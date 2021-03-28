package br.com.bmo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bmo.taskmanager.model.Task;

public interface TaskAPIRepository extends JpaRepository<Task, Integer>{

}
