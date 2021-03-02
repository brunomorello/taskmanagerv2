package br.com.bmo.taskmanager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.orm.Task;
import br.com.bmo.taskmanager.orm.TaskProjection;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer>{
	@Query("SELECT t from Task t WHERE month(t.createdAt) = :month")
	List<Task> findTasksByMonth(Integer month);
	
	// Same as JPQL SELECT t FROM Task T JOIN t.category c WHERE c.name = :name
	List<Task> findByCategoryName(String name);
	
	@Query("SELECT t from Task t JOIN t.category c WHERE c.name LIKE :name")
	List<Task> findByCategoryNameLike(String name);
	
	// Native Query
	@Query(value = "SELECT * FROM tasks t WHERE due_date >= :period", nativeQuery = true)
	List<Task> findTaskDueDateIsEqualsOrGreaterThan(LocalDateTime period);
	
	// JPA Projection (Using interface based projections. It is possible to use class-based projections)
	//    class-based projection can be called DTO (Data Transfer Object)
	// 		and could be useful on views to apply projections  
	@Query(value = "SELECT t.description, s.name as statusName FROM tasks t JOIN status s ON (t.status_id = s.id)", nativeQuery = true)
	List<TaskProjection> findTaskByDescriptionAndStaus();
}
