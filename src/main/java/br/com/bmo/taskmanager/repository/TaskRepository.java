package br.com.bmo.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.orm.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
}
