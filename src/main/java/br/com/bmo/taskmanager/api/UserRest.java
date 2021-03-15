package br.com.bmo.taskmanager.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmo.taskmanager.dto.RequestNewUser;
import br.com.bmo.taskmanager.model.User;
import br.com.bmo.taskmanager.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRest {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/")
	public User createUser(@Valid @RequestBody RequestNewUser request) {
		User user = request.toUser();
		userRepository.save(user);
		return user;
	}
}
