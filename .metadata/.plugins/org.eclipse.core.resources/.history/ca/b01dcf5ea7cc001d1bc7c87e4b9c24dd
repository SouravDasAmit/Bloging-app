package com.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppContants;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController 
{
	@Autowired
	private PostService postService ;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
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
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam (value="pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam (value="pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam (value="sortBy", defaultValue = AppContants.SHORT_BY, required = false) String sortBy,
			@RequestParam (value="sortDir", defaultValue = AppContants.SHORT_DIR, required = false) String sortDir)

	{
		//System.out.println(pageNumber+" "+pageSize);
		PostResponse allPosts= this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
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
	public ResponseEntity<PostDto> updatedPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto updatedPost= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	//search
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByTitleContaining(@PathVariable String keyword)
	{
		List<PostDto> seachedPosts= this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(seachedPosts, HttpStatus.OK);
	}
	//upload image
	@PostMapping("/image/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException
	{
		PostDto postDto= this.postService.getPostById(postId);
		System.out.println(postId);
		String fileName= this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
}
