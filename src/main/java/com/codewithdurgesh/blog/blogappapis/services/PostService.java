package com.codewithdurgesh.blog.blogappapis.services;

import java.util.List;

import com.codewithdurgesh.blog.blogappapis.payloads.PostDto;
import com.codewithdurgesh.blog.blogappapis.payloads.PostResponse;

public interface PostService {

    PostDto getPostById(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
