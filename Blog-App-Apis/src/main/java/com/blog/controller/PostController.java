package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController 
{
	@Autowired
	private PostService postService ;
	//create post
	@PostMapping("/user/{u_id}/categori/{categoriId}/")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer u_id, @PathVariable Integer categoriId)
	{
		//System.out.println(postDto.getTitle()+" "+postDto.getContent()+" "+u_id);
		PostDto createPost= this.postService.createPost(postDto, u_id, categoriId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	//get post by categori
	@GetMapping("/categori/{categoriId}")
	public ResponseEntity<List<PostDto>> getPostsByCategori(@PathVariable Integer categoriId)
	{
		List<PostDto> posts= this.postService.getAllPostByCategori(categoriId);
//		for(PostDto t: posts) {
//			System.out.println(t.getTitle());
//		}
//		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//get post by user
	@GetMapping("/user/{u_id}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer u_id)
	{
		List<PostDto> posts= this.postService.getAllPostByUser(u_id);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	//get all posts
	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPost()
	{
		List<PostDto> allPosts= this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}
	//get post by post id
	@GetMapping("/{postId}")
	public PostDto getPostById(@PathVariable Integer postId)
	{
		PostDto postDto= this.postService.getPostById(postId);
		return postDto; 
	}
	//delete a post
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Delete successfully", true),HttpStatus.OK);

	}
	//update post
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto updatedPost= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
}
