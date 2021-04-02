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

import br.com.bmo.taskmanager.config.security.TokenService;
import br.com.bmo.taskmanager.controller.dto.AuthForm;
import br.com.bmo.taskmanager.controller.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService; 
	
	@PostMapping
	public ResponseEntity<?> auth(@Valid @RequestBody AuthForm form) {
		
		try {
			UsernamePasswordAuthenticationToken userAuth = form.parse();
			Authentication authentication = authManager.authenticate(userAuth);
			String token = tokenService.generate(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
