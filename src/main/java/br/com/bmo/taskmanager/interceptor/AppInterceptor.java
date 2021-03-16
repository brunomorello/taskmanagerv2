package br.com.bmo.taskmanager.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AppInterceptor implements HandlerInterceptor{
	
	public static List<AppAccess> appAccessHistory = new ArrayList<AppAccess>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		AppAccess appAccess = new AppAccess();
		appAccess.setPath(request.getRequestURI());
		appAccess.setCurentDateTime(LocalDateTime.now());
		
		request.setAttribute("appAccess", appAccess);
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		AppAccess appAccess = (AppAccess) request.getAttribute("appAccess");
		appAccess.setDuration(Duration.between(appAccess.getCurentDateTime(), LocalDateTime.now()));
		appAccessHistory.add(appAccess);
	}
}
