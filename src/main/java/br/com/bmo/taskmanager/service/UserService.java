package br.com.bmo.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.model.User;
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
	
	public User getUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public void listUsersFoundByName(String firstName) {
		List<User> usersFoundList = userRepository.findByFirstNameLike(firstName);
		usersFoundList.stream().forEach(System.out::println);
	}
}
