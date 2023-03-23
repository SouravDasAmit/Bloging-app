package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoriDto;

public interface CategoriService 
{
	CategoriDto createCategori(CategoriDto categoriDto);
	CategoriDto updateCategori(CategoriDto categoriDto, Integer categoriId);
	void deleteCategori(Integer categoriId);
	CategoriDto getCategori(Integer categoriId);
	List<CategoriDto> getAllCategori();
}
