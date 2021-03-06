package br.com.bmo.taskmanager.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthForm {

	private String username;
	private String password;
	
	public AuthForm() {
	}
	
	public AuthForm(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken parse() {
		return new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
	}
	
}
