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
public class PaymentDto {
    private Integer id;
    private Double quantity;
    private Integer month;
    private Integer year;
    private String concept;
    private List<Player> players;
}
