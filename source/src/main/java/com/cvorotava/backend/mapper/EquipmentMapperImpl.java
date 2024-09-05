package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.EquipmentDto;
import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EquipmentMapperImpl implements IEquipmentMapper {
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentMapperImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public EquipmentDto equipmentToDTO(Equipment equipment) {
        if (equipment == null) {
            return null;
        }

        return EquipmentDto.builder()
                .id(equipment.getId())
                .size(equipment.getSize())
                .type(equipment.getType())
                .color(equipment.getColor())
                .players(equipment.getPlayers())
                .build();
    }

    @Override
    public Equipment equipmentDTOToEntity(EquipmentDto equipmentDto) {
        if (equipmentDto == null) {
            return null;
        }

        Equipment equipment = equipmentRepository.findById(equipmentDto.getId()).orElseThrow(() -> new NotFoundException("That equipment couldn't be found"));

        return Equipment.builder()
                .id(equipment.getId())
                .size(equipmentDto.getSize())
                .type(equipmentDto.getType())
                .color(equipmentDto.getColor())
                .players(equipmentDto.getPlayers())
                .build();
    }

    @Override
    public List<EquipmentDto> equipmentListToDTOList(List<Equipment> equipmentList) {
        if (equipmentList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EquipmentDto> equipmentDtoList = new ArrayList<>();

        for (Equipment equipment : equipmentList) {
            equipmentDtoList.add(equipmentToDTO(equipment));
        }

        return equipmentDtoList;
    }

    @Override
    public List<Equipment> dtoListToEquipmentList(List<EquipmentDto> equipmentDtoList) {
        if (equipmentDtoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Equipment> equipmentList = new ArrayList<>();

        for (EquipmentDto equipmentDto : equipmentDtoList) {
            equipmentList.add(equipmentDTOToEntity(equipmentDto));
        }

        return equipmentList;
    }
}
