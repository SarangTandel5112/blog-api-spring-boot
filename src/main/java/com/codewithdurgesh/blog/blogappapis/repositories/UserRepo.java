package com.codewithdurgesh.blog.blogappapis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.blogappapis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
