package com.codewithdurgesh.blog.blogappapis.services;

import com.codewithdurgesh.blog.blogappapis.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);

}
