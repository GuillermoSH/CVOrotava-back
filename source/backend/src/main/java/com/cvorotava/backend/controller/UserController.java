package com.cvorotava.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.service.UserService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/asignaturas")
public class UserController {
	@Autowired
	private UserService userservice;
	
	@GetMapping
	public List<User> getAsignaturas() {
		return userservice.findAll();
	}
}
