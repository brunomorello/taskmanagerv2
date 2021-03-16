package br.com.bmo.taskmanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.bmo.taskmanager.interceptor.AppInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AppInterceptor()).addPathPatterns("/**");
	}
}
