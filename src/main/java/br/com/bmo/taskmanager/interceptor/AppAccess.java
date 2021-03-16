package br.com.bmo.taskmanager.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;

public class AppAccess {

	private String path;
	private LocalDateTime curentDateTime;
	private Duration duration;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public LocalDateTime getCurentDateTime() {
		return curentDateTime;
	}
	public void setCurentDateTime(LocalDateTime curentDateTime) {
		this.curentDateTime = curentDateTime;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
