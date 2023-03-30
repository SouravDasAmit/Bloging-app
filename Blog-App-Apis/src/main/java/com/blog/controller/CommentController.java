package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController 
{
	@Autowired
	private CommentService commentService;
	
	//create comment
	@PostMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto comment,  
			@PathVariable Integer userId, @PathVariable Integer postId)
	{
		CommentDto commentDto= this.commentService.createComment(comment, userId, postId);
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
	}
	
	//delete comment
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment delete sucessfully", true),HttpStatus.OK);
	}
	
	//update comment
	@PutMapping("/update/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commnetDto, @PathVariable Integer commentId)
	{
		CommentDto commentDto= this.commentService.updateComment(commnetDto, commentId);
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
	}

}
