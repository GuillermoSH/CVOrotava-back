package com.cvorotava.backend.controller;

import java.util.List;

import com.cvorotava.backend.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.service.UserService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService userservice;
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userservice.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(userservice.findById(id));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userservice.findByUsername(username));
	}

	@PostMapping("/save")
	public ResponseEntity<UserDto> save(@RequestBody User user) {
		return ResponseEntity.ok(userservice.save(user));
	}

	@DeleteMapping("/{userDto}")
	public void delete(@PathVariable UserDto userDto) {
		userservice.delete(userDto);
	}
}
