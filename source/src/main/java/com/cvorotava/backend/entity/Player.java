package com.cvorotava.backend.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String dni;
	private String name;
	private String surname1;
	private String surname2;
	private Integer telephone;
	private String email;
	private String address;
	private String birthday;
	private String category;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Payment> payments;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Equipment> equipments;
}