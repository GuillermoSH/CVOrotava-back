package com.cvorotava.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "player")
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "DNI must be fulfilled")
    @Column(unique = true)
    private String dni;

    @NotBlank(message = "Name must be fulfilled")
    private String name;

    @NotBlank(message = "First surname must be fulfilled")
    private String surname1;

    private String surname2;

    @NotBlank(message = "Telephone must be fulfilled")
    @Pattern(regexp = "^([(]?[+]?[(]?[0-9]{2,3}[)]?[- .]?)?[6-9]\\d{8}$", message = "Size must be fulfilled")
    private String telephone;

    @NotBlank(message = "Email must be fulfilled")
    @Pattern(regexp = "^([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,},\\s*)*[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Address must be fulfilled")
    private String address;

    @NotBlank(message = "Birthday must be fulfilled")
    private String birthday;

    @NotBlank(message = "Category must be fulfilled")
    private String category;

    private String image;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Payment> payments;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Equipment> equipments;
}