package com.codewithdurgesh.blog.blogappapis.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blogappapis.entities.Category;
import com.codewithdurgesh.blog.blogappapis.entities.Post;
import com.codewithdurgesh.blog.blogappapis.entities.User;
import com.codewithdurgesh.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blogappapis.payloads.PostDto;
import com.codewithdurgesh.blog.blogappapis.payloads.PostResponse;
import com.codewithdurgesh.blog.blogappapis.repositories.CategoryRepo;
import com.codewithdurgesh.blog.blogappapis.repositories.PostRepo;
import com.codewithdurgesh.blog.blogappapis.repositories.UserRepo;
import com.codewithdurgesh.blog.blogappapis.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto getPostById(Integer postId) {
        System.out.println("=============");
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        System.out.println(post.getComments() + "========");

        PostDto ptest = this.modelMapper.map(post, PostDto.class);
        // System.out.println(ptest.getComments() + "========");
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> postDtos = pagePost.getContent();
        List<PostDto> postList = postDtos.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .toList();

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();
    }

}
