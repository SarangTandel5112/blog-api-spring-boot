package com.codewithdurgesh.blog.blogappapis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blogappapis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blogappapis.payloads.CommentDto;
import com.codewithdurgesh.blog.blogappapis.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {
        CommentDto commentDto = this.commentService.createComment(comment, postId);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment  deleted successfully", true),
                HttpStatus.OK);
    }

}
