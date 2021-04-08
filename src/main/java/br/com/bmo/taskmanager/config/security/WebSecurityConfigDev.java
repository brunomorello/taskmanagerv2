package br.com.bmo.taskmanager.config.security;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.bmo.taskmanager.repository.UserRepository;

@Configuration
@EnableWebSecurity
@Profile(value = { "dev" })
public class WebSecurityConfigDev extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private CustomAuthenticationService custAuthService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Authorization Configs
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().permitAll()
			.and().csrf().disable();
//			.and()
//				.formLogin(form -> form
//					.loginPage("/login")
//					.defaultSuccessUrl("/home", true)
//					.permitAll()
//				)
//				.logout(logout -> logout
//						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//						.logoutSuccessUrl("/home")
//				)
//				.csrf().disable();
	}
	
	// Authentication Configs
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(custAuthService)
			.passwordEncoder(new BCryptPasswordEncoder());
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.passwordEncoder(encoder);
	}

	// Statict Resources Configs
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO - fix swagger ui
		web.ignoring()
			.antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui/index.html**", "/webjars/**", "/actuator/health");
	}
}
