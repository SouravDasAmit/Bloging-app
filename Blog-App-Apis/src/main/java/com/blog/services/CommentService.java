package com.blog.services;

import com.blog.payloads.CommentDto;

public interface CommentService 
{
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer u_id);
	void deleteComment(Integer commentId);
	CommentDto updateComment(CommentDto commentDto, Integer commentId);
}
