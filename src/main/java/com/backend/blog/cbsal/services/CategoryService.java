package com.backend.blog.cbsal.services;

import java.util.List;

import com.backend.blog.cbsal.payloads.CategoryDto;

public interface CategoryService {

	// create
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	void deleteCategory(Integer categoryId);

	// get
	CategoryDto getSingleCategory(Integer categoryId);

	// getAll
	List<CategoryDto> getAllCategories();
}
