package br.com.bmo.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanager.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	List<User> findByFirstNameLike(String firstName);
	User findByUsername(String username);
}
