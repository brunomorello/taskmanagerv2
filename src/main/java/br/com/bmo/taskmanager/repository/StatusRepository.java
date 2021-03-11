package br.com.bmo.taskmanager.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer>{

	@Cacheable("taskStatus")
	Status findByName(String name);
}
