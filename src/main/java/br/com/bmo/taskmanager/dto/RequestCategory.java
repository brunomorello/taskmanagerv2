package br.com.bmo.taskmanager.dto;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanager.model.Category;

public class RequestCategory {
	
	@NotBlank
	private String categoryName;
	private String categoryId;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Category parse() {
		Category category = new Category();
		category.setName(getCategoryName());
		if (getCategoryId() != null)
			category.setId(Integer.valueOf(getCategoryId()));
		return category;
	}
	
}
