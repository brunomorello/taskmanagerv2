package br.com.bmo.taskmanager.dto;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanager.model.Category;

public class RequestCategory {
	
	@NotBlank
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category parse() {
		Category category = new Category();
		category.setName(getCategoryName());
		return category;
	}
	
}
