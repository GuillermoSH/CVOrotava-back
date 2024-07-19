package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.EquipmentDto;
import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.entity.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IEquipmentMapper {
    EquipmentDto equipmentToDTO(Equipment equipment);

    @InheritInverseConfiguration
    Equipment equipmentDTOToEntity(EquipmentDto equipmentDto);

    List<EquipmentDto> equipmentListToDTOList(List<Equipment> equipmentList);

    List<Equipment> dtoListToEquipmentList(List<EquipmentDto> equipmentDtoList);
}
