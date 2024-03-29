package com.blog.services.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.controller.PostController;
import com.blog.entities.Categori;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoriRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoriRepo categoriRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer u_id, Integer categoriId) {
		User user= this.userRepo.findById(u_id).orElseThrow(() ->new ResourceNotFoundException("User", "userId", u_id));
		Categori categori= this.categoriRepo.findById(categoriId).orElseThrow(() ->new ResourceNotFoundException("categori", "categoriId", categoriId));
		Post post= this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		LocalDateTime now = LocalDateTime.now();
	    Date date = new Date(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()).getTime());
	    post.setAddedDate(date);
		post.setUser(user);
		post.setCategori(categori);
		Post newPost= this.postRepo.save(post);
		//System.out.println(this.modelMapper.map(newPost, PostDto.class));
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) 
	{
		Post post= this.postRepo.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		//if you want to update more things write code here 
		//System.out.println(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		Post updatedPost= this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) 
	{
	Post post= this.postRepo.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post", "Post id", postId));
	this.postRepo.delete(post);

	}

	@Override
	public PostDto getPostById(Integer postId) 
	{
		Post post= this.postRepo.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post", "Post id", postId));
		PostDto postDto= this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) 
	{
		Sort sort= (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		Pageable p= PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost= this.postRepo.findAll(p);
		List<Post> allPosts= pagePost.getContent();
		List<PostDto> allPostDtos= allPosts.stream().map(Post ->modelMapper.map(Post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(allPostDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostByCategori(Integer categoriId) 
	{
		//Pageable p= PageRequest.of(pageNumber, pageSize);
		
		Categori cat= this.categoriRepo.findById(categoriId).orElseThrow(()->new ResourceNotFoundException("Categori", "Categori id", categoriId));
		List<Post> posts= postRepo.findByCategori(cat);
		
//		for(Post t: posts) {
//			System.out.println(t.getTitle());
//		}
		
		List<PostDto> postDto = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer u_id) 
	{
		User user= this.userRepo.findById(u_id).orElseThrow(()->new ResourceNotFoundException("User", "User Id", u_id));
		List<Post> posts= this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) 
	{
		List<Post> posts= this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos= posts.stream().map(Post-> modelMapper.map(Post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
		
	}

}
