package com.cvorotava.backend.repository;

import com.cvorotava.backend.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query(value = "SELECT e FROM Equipment e ORDER BY e.size, e.type, e.color")
    List<Equipment> findAll();

    @Query(value = "SELECT e FROM Equipment e WHERE CONCAT_WS(' ', e.size, e.type, e.color) LIKE %:search%")
    List<Equipment> searchLike(@Param("search") String search);
}
