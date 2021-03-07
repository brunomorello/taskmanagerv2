package br.com.bmo.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer>{

	Status findByName(String name);
}
