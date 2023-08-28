package com.cvorotava.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvorotava.backend.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	Optional<Player> findById(Integer id);
	List<Player> findByCategory(String category);
}
