package com.cvorotava.backend.entity;

import com.cvorotava.backend.enums.EquipmentSize;
import com.cvorotava.backend.enums.EquipmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "equipment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"size", "type", "color"})
})
public class Equipment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Size must be fulfilled")
    @Enumerated(EnumType.STRING)
    private EquipmentSize size;

    @NotBlank(message = "Type must be fulfilled")
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @NotBlank(message = "Color must be fulfilled")
    private String color;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "player_equipments",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;
}