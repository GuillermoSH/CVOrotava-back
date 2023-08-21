package com.cvorotava.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvorotava.backend.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findById(String id);
	User findByUsername(String username);
}
