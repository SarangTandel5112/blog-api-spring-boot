package com.codewithdurgesh.blog.blogappapis.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blogappapis.entities.Category;
import com.codewithdurgesh.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blogappapis.payloads.CategoryDto;
import com.codewithdurgesh.blog.blogappapis.repositories.CategoryRepo;
import com.codewithdurgesh.blog.blogappapis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return this.modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();

        List<CategoryDto> categoryDtos = categories.stream()
                .map((category) -> this.modelMapper.map(category, CategoryDto.class)).toList();

        return categoryDtos;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category savedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(category);
    }

}
