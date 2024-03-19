package com.codewithdurgesh.blog.blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.blogappapis.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
