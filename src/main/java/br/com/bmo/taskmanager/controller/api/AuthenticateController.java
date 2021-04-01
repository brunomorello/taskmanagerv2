package br.com.bmo.taskmanager.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmo.taskmanager.controller.dto.AuthForm;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
	
	@PostMapping
	public ResponseEntity<?> auth(@Valid @RequestBody AuthForm form) {
		System.out.println(form.getUsername());
		System.out.println(form.getPassword());
		return ResponseEntity.ok().build();
	}
}
