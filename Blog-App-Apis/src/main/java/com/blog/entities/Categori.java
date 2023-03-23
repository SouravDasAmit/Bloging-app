package com.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "categories")
@Getter
@Setter
@NoArgsConstructor
public class Categori {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoriId;
	//@Column(name= "Title")
	private String categoriTitle;
	//@Column(name= "Description")
	private String categoriDescription;

}
