package com.codewithdurgesh.blog.blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.blogappapis.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
