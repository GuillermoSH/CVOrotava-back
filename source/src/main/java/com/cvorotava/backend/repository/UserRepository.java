package com.cvorotava.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvorotava.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findById(Integer id);
	User findByUsername(String username);
}
