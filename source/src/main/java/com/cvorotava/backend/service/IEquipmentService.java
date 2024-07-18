package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEquipmentService {
    List<Equipment> findAll();

    Equipment findById(Integer id);

    List<Equipment> searchLike(String search);

    Equipment save(Equipment entity);

    Equipment dropPlayerFromEquipment(Integer equipment_id, Integer player_id);

    void delete(Integer id);
}
