package com.codewithdurgesh.blog.blogappapis.services;

import java.util.List;

import com.codewithdurgesh.blog.blogappapis.payloads.CategoryDto;

public interface CategoryService {

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getCategories();

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    void deleteCategory(Integer categoryId);

} 
