package br.com.bmo.taskmanager.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmo.taskmanager.controller.dto.AuthForm;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<?> auth(@Valid @RequestBody AuthForm form) {
		UsernamePasswordAuthenticationToken userAuth = form.parse();
		
		try {
			Authentication authentication = authManager.authenticate(userAuth);
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println(form.getUsername());
		System.out.println(form.getPassword());
		return ResponseEntity.ok().build();
	}
}
