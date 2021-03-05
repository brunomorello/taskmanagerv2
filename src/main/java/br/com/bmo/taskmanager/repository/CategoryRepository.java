package br.com.bmo.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
