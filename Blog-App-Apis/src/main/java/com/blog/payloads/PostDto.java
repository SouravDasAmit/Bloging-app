package com.blog.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto 
{
	private Integer postId;
	@NotEmpty
	@Size(message="Please provide a post title.")
	private String title;
	@NotEmpty
	@Size(min=10, message="Write details about post title")
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoriDto categori;
	private UserDto user;
	private Set<CommentDto> comments= new HashSet<>();


}
