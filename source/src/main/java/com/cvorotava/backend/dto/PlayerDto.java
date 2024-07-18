package com.cvorotava.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String dni;
    private String name;
    private String surname1;
    private String surname2;
    private Integer telephone;
    private String email;
    private String address;
    private String birthday;
    private String category;
}
