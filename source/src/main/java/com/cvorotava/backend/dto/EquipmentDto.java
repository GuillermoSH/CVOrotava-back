package com.cvorotava.backend.dto;

import com.cvorotava.backend.entity.Player;
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
    private String size;
    private String type;
    private String color;
    private String useFor;
    private List<Player> players;
}
