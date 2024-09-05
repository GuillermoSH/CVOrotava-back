package com.cvorotava.backend.dto;

import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.enums.EquipmentSize;
import com.cvorotava.backend.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDto {
    private Integer id;
    private EquipmentSize size;
    private EquipmentType type;
    private String color;
    private List<Player> players;
}
