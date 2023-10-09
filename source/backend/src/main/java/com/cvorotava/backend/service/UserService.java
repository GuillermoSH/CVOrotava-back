package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;

	public User save(User entity) {
		return userrepository.save(entity);
	}

	public Optional<User> findByUsername(String username) {
		return userrepository.findByUsername(username);
	}

	public List<User> findAll() {
		return userrepository.findAll();
	}

	public Boolean remove(Integer id) {
		Optional<User> currentUser = userrepository.findById(id);
		if (currentUser.isPresent()) {
			userrepository.deleteById(id);
			return true;
		}
		return false;
	}
}
