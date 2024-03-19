package com.codewithdurgesh.blog.blogappapis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blogappapis.config.AppConstants;
import com.codewithdurgesh.blog.blogappapis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blogappapis.payloads.PostDto;
import com.codewithdurgesh.blog.blogappapis.payloads.PostResponse;
import com.codewithdurgesh.blog.blogappapis.services.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postDto = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDto = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

        PostResponse postDto = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postDto, HttpStatus.OK);

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post entity deleted successfully", true),
                HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

}
