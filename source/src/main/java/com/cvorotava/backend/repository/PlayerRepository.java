package com.cvorotava.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cvorotava.backend.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	Optional<Player> findById(Integer id);
	List<Player> findByCategory(String category);
	List<Player> findAllOrderedBy(Sort sort);
	Optional<Player> findByDni(String dni);
	
	@Query(value="SELECT COUNT(p) FROM Player p WHERE p.category LIKE '%MAS%'")
	String countMasPlayers();
	
	@Query(value="SELECT COUNT(p) FROM Player p WHERE p.category LIKE '%FEM%'")
	String countFemPlayers();
	
	@Query(value="SELECT p FROM Player p WHERE CONCAT_WS(' ', p.dni, p.name, p.surname1, p.surname2, p.telephone, p.email, p.address, p.birthday, p.category) LIKE %:search%")
	List<Player> searchLike(@Param("search") String search);
	
	@Modifying
	@Query(value="DELETE FROM payment_players WHERE players_id = :id", nativeQuery = true)
	void deleteRelations(@Param("id") Integer id);
	
	@Query(value="DELETE FROM payment_players WHERE players_id = :id", nativeQuery = true)
	void deleteAllRelations();
}
