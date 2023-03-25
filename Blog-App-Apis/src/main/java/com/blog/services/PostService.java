package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface PostService 
{
	//create post
	PostDto createPost(PostDto postDto, Integer u_id, Integer categoriId);
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	//delete post
	void deletePost(Integer postId);
	//get single post
	PostDto getPostById(Integer postId);
	//get all posts
	List<PostDto> getAllPost();
	//get all posts by categori
	List<PostDto> getAllPostByCategori(Integer categoriId);
	//get all posts by user
	List<PostDto> getAllPostByUser(Integer u_id);
	//find a post by search
	List<Post> searchPost(String keyword);
	
}
