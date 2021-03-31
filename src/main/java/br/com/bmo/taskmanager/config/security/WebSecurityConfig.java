package br.com.bmo.taskmanager.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	// Authorization Configs
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/tasks/").permitAll()
			.antMatchers(HttpMethod.GET, "/api/tasks/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/categories/").permitAll()
			.antMatchers(HttpMethod.GET, "/api/categories/*").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin(form -> form
					.loginPage("/login")
					.defaultSuccessUrl("/home", true)
					.permitAll()
				)
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
						.logoutSuccessUrl("/home")
				)
				.csrf().disable();
	}
	
	// Authentication Configs
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder);
	}

	// Statict Resources Configs
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
}
