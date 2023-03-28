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
import com.blog.payloads.CategoriDto;
import com.blog.services.CategoriService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categori")
public class CategoriController {
	@Autowired
	private CategoriService categoriService;
	@PostMapping("/")
	public ResponseEntity<CategoriDto> createCategori(@Valid @RequestBody CategoriDto categoriDto)
	{
		
		CategoriDto createCategori= this.categoriService.createCategori(categoriDto);
		return new ResponseEntity<CategoriDto>(createCategori, HttpStatus.CREATED);
	}
	@PutMapping("/{catId}")
	public ResponseEntity<CategoriDto> updateCategori(@Valid @RequestBody CategoriDto categoriId, @PathVariable Integer catId)
	{
		CategoriDto updatedCategori= this.categoriService.updateCategori(categoriId, catId);
		return new ResponseEntity<CategoriDto>(updatedCategori, HttpStatus.OK);
	}
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategori(@PathVariable Integer catId)
	{
		this.categoriService.deleteCategori(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Categori Delete Successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoriDto> getCategori(@PathVariable Integer catId)
	{
		CategoriDto CategoriDto= this.categoriService.getCategori(catId);
		return new ResponseEntity<CategoriDto>(CategoriDto, HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoriDto>> getAllCetegori()
	{
		return ResponseEntity.ok(this.categoriService.getAllCategori());
	}
	

}
