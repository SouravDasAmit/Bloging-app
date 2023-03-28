package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Categori;
import com.blog.payloads.CategoriDto;
import com.blog.repositories.CategoriRepo;
import com.blog.services.CategoriService;
import com.blog.exceptions.*;
@Service
public class CategoriServiceImpl implements CategoriService {
	@Autowired
	private CategoriRepo categoriRepo;
	@Autowired
	private ModelMapper modelMaper;
	@Override
	public CategoriDto createCategori(CategoriDto categoriDto) {
		Categori cat= this.modelMaper.map(categoriDto, Categori.class);
		Categori addCat= this.categoriRepo.save(cat);
		return this.modelMaper.map(addCat, CategoriDto.class);
	}

	@Override
	public CategoriDto updateCategori(CategoriDto categoriDto, Integer categoriId) {
		Categori cat= this.categoriRepo.findById(categoriId).orElseThrow(() -> new ResourceNotFoundException("Categori", "categori id", categoriId));
		cat.setCategoriTitle(categoriDto.getCategoriTitle());
		cat.setCategoriDescription(categoriDto.getCategoriDescription());
		Categori updateCat= categoriRepo.save(cat);
		return this.modelMaper.map(updateCat, CategoriDto.class);
	}

	@Override
	public void deleteCategori(Integer categoriId) {
		Categori cat= this.categoriRepo.findById(categoriId).orElseThrow(()-> new ResourceNotFoundException("categori", "categori id", categoriId));
		this.categoriRepo.delete(cat);
	}

	@Override
	public CategoriDto getCategori(Integer categoriId) {
		Categori cat= this.categoriRepo.findById(categoriId).orElseThrow(()-> new ResourceNotFoundException("Categori", "categori id", categoriId));
		
		return this.modelMaper.map(cat, CategoriDto.class);
	}

	@Override
	public List<CategoriDto> getAllCategori() {
		List<Categori> categories= this.categoriRepo.findAll();
		List<CategoriDto> catDtos= categories.stream().map((cat)-> this.modelMaper.map(cat, CategoriDto.class)).collect(Collectors.toList()); //map er madhome ak ak cetegori niyeci r modelMapper deye dto te convert korci, pore list er modhe collect korci
		return catDtos;
	}

}
