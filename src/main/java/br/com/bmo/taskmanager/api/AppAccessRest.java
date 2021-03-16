package br.com.bmo.taskmanager.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmo.taskmanager.interceptor.AppAccess;
import br.com.bmo.taskmanager.interceptor.AppInterceptor;

@RestController
@RequestMapping("/security")
public class AppAccessRest {

	@GetMapping("/appAccess")
	public List<AppAccess> getAppAccessList() {
		return AppInterceptor.appAccessHistory;
		// Spring Boot Actuator - read docs
	}
}
