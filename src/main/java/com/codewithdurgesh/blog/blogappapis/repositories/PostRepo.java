package com.codewithdurgesh.blog.blogappapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.blogappapis.entities.Category;
import com.codewithdurgesh.blog.blogappapis.entities.Post;
import com.codewithdurgesh.blog.blogappapis.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
