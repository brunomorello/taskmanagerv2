package br.com.bmo.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.orm.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	List<User> findByFirstNameLike(String firstName);
}
