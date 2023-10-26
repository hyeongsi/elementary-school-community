package com.example.project.dto;

import org.apache.ibatis.type.Alias;

@Alias("NoticeCategoryDto")
public class NoticeCategoryDto {

	private Long categoryId;
	private String categoryName;
	
	public NoticeCategoryDto(Long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
}





