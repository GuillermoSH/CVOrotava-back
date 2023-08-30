package com.cvorotava.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cvorotava.backend.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	Optional<Player> findById(Integer id);
	List<Player> findByCategory(String category);
	
	@Query(value="SELECT p FROM Player p")
	List<Player> findAllOrderedByName(Sort sort);
}
