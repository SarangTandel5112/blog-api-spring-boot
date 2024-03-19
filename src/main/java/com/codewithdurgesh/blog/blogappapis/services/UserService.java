package com.codewithdurgesh.blog.blogappapis.services;

import java.util.List;

import com.codewithdurgesh.blog.blogappapis.payloads.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
