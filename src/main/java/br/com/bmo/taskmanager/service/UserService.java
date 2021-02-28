package br.com.bmo.taskmanager.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.orm.User;
import br.com.bmo.taskmanager.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepositoy) {
		this.userRepository = userRepositoy;
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) {
		Optional<User> findById = userRepository.findById(id);
		return findById.get();
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
}
