package com.cvorotava.backend.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Equipement.findAll", query = "SELECT e FROM Equipement e")
public class Equipement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String size;
	private String type;
	private String color;
	private String use_for;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Player> players;
	
	public Equipement() {
		
	}

	public Equipement(Integer id, String size, String type, String color, String use_for, List<Player> players) {
		this.id = id;
		this.size = size;
		this.type = type;
		this.color = color;
		this.use_for = use_for;
		this.players = players;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUse() {
		return use_for;
	}

	public void setUse(String use_for) {
		this.use_for = use_for;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}