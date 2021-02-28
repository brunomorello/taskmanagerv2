package br.com.bmo.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.orm.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
	@Query("SELECT t from Task t WHERE month(t.createdAt) = :month")
	List<Task> findTasksByMonth(Integer month);
}
