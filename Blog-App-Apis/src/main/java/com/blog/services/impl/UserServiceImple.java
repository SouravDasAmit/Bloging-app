package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.config.AppContants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;
import com.blog.repositories.*;
import com.blog.exceptions.*;
@Service
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user= this.dtoToUser(userDto);
		User saveUser= this.userRepo.save(user);
		return this.UsertoUserDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setU_name(userDto.getU_name());
		System.out.println(user.getU_name() +userDto.getU_name());
		user.setU_id(user.getU_id());
		System.out.println(userDto.getU_id());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updateUser= this.userRepo.save(user);
		UserDto userDto1= this.UsertoUserDto(updateUser);
		
		return userDto1;
//		User user1= this.dtoToUser(userDto);
//		return this.UsertoUserDto(this.userRepo.save(user1));
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		return this.UsertoUserDto(user);
	}
	

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users= userRepo.findAll();
		List<UserDto> userDtos =users.stream().map(user->this.UsertoUserDto(user)).collect(Collectors.toList()); 
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user= this.modelMapper.map(userDto, User.class);
		return user;
	}
	private UserDto UsertoUserDto(User user)
	{
		UserDto userDto= this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user= this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role = this.roleRepo.findById(AppContants.ROLE_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
