package com.cvorotava.backend.dto;

import com.cvorotava.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private List<Role> roles;
}
