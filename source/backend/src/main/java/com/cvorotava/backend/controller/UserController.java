package com.cvorotava.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.service.UserService;

@CrossOrigin(origins = { "http://192.168.1.27:4200/", "http://localhost:4200/",
		"https://zm220cwj-4200.euw.devtunnels.ms/", "https://c24djzb4-4200.uks1.devtunnels.ms/" })
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService userservice;

	@GetMapping
	public List<User> getUsers() {
		return userservice.findAll();
	}

	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userservice.findByUsername(username).get();
	}

	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody User user) {
		Optional<User> login_user = userservice.findByUsername(user.getUsername());
		HashMap<String, Object> response = new HashMap<>();

		if (login_user.isPresent()) {
			if (login_user.get().getPassword().equals(user.getPassword())) {
				response.put("user_log", login_user);
				response.put("message", "Inicio de sesión correcto");
				return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("message", "La contraseña no coincide con el usuario.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {			
			response.put("message", "El usuario no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}
}
