package com.cvorotava.backend.dto;

import com.cvorotava.backend.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Double quantity;
    private Integer month;
    private Integer year;
    private String concept;
    private List<Player> players;
}
