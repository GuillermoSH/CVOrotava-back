package com.cvorotava.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cvorotava.backend.entity.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement, Integer> {
	@Query(value="SELECT e FROM Equipement e ORDER BY e.size, e.type, e.use_for, e.color")
	List<Equipement> findAll();
	Optional<Equipement> findById(Integer id);
	
	@Query(value="SELECT e FROM Equipement e WHERE CONCAT_WS(' ', e.size, e.type, e.use_for, e.color) LIKE %:search%")
	List<Equipement> searchLike(@Param("search") String search);
}
