package com.codewithdurgesh.blog.blogappapis.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blogappapis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blogappapis.payloads.UserDto;
import com.codewithdurgesh.blog.blogappapis.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        // return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        return new ResponseEntity<UserDto>(this.userService.getUserById(userId), HttpStatus.OK);
    }
}
