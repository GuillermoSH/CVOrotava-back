package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.EquipmentDto;
import com.cvorotava.backend.entity.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEquipmentService {
    List<EquipmentDto> findAll();

    EquipmentDto findById(Integer id);

    List<EquipmentDto> searchLike(String search);

    EquipmentDto save(Equipment entity);

    EquipmentDto dropPlayerFromEquipment(Integer equipment_id, Integer player_id);

    void delete(EquipmentDto equipmentDto);
}
