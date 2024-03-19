package com.codewithdurgesh.blog.blogappapis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blogappapis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blogappapis.payloads.CategoryDto;
import com.codewithdurgesh.blog.blogappapis.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<CategoryDto>(this.categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto,
            @PathVariable Integer categoryId) {
        CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

}
