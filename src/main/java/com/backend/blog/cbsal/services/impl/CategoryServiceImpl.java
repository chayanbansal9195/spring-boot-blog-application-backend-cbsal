package com.backend.blog.cbsal.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.cbsal.entities.Category;
import com.backend.blog.cbsal.exceptions.ResourceNotFoundException;
import com.backend.blog.cbsal.payloads.CategoryDto;
import com.backend.blog.cbsal.repositories.CategoryRepo;
import com.backend.blog.cbsal.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category createdCategory = this.modelMapper.map(categoryDto, Category.class);
		Category addedNewCategory = this.categoryRepo.save(createdCategory);
		return this.modelMapper.map(addedNewCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category updateCategory = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		updateCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		updateCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepo.save(updateCategory);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category deleteCategory = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.delete(deleteCategory);

	}

	@Override
	public CategoryDto getSingleCategory(Integer categoryId) {

		Category getSingleCategory = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.modelMapper.map(getSingleCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> getAllCategories = this.categoryRepo.findAll();
		List<CategoryDto> getAllCategoriesDto = getAllCategories.stream()
				.map((singleCategory) -> this.modelMapper.map(singleCategory, CategoryDto.class))
				.collect(Collectors.toList());
		return getAllCategoriesDto;
	}

}
