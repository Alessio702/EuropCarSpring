package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PubliRestApiController {
	private UserRepository userRepository;
	
	public PubliRestApiController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	// Available to allAuthenticated
	@GetMapping("/test1")
	public String test1() {
		return "API test";
	}
		
	
	// Available to ROLE_ADMIN
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	
}
