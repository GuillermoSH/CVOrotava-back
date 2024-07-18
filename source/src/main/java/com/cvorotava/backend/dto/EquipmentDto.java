package com.cvorotava.backend.dto;

import com.cvorotava.backend.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    private String size;
    private String type;
    private String color;
    private String useFor;
    private List<Player> players;
}
