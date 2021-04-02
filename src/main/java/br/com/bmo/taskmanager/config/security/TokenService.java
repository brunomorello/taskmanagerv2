package br.com.bmo.taskmanager.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanager.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${taskmanager.api.jwt.expiration}")
	private String expirationTime;
	
	@Value("${taskmanager.api.jwt.secret}")
	private String secret;

	public String generate(Authentication authentication) {
		User userLoggedIn = (User) authentication.getPrincipal();
		
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + Long.parseLong(expirationTime));
		
		return Jwts.builder()
				.setIssuer("taskmanagerv2_api")
				.setSubject(userLoggedIn.getUsername())
				.setIssuedAt(currentDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsername(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return claims.getSubject();
		} catch (Exception e) {
			return null;
		}
	}

}
