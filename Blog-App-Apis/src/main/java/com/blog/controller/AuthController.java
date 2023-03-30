package com.blog.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.ApiException;
import com.blog.payloads.JwtAuthRequest;
import com.blog.payloads.JwtAuthResponse;
import com.blog.payloads.UserDto;
import com.blog.security.JwtTolenHelper;
import com.blog.services.UserService;

@RestController
@RequestMapping("/auth/")
public class AuthController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTolenHelper jwtTolenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> creteToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		System.out.println(request.getEmail() +" "+ request.getPassword());
		this.authenticate(request.getEmail(), request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
		String token= this.jwtTolenHelper.generateToken(userDetails);
		JwtAuthResponse response= new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		
	}
	private void authenticate(String email, String password) throws Exception 
	{
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(email, password);
		try 
		{
			
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}
		catch (BadCredentialsException e) 
		{
			System.out.println("Invalid details!");
			throw new ApiException("Invalid username and password!");
		}

	}
	
	//register new user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
	{
		UserDto registerNewUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerNewUser, HttpStatus.CREATED);
	}

}