package com.cvorotava.backend.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double quantity;
	private Integer month;
	private Integer year;
	private String concept;
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Player> players;
	
	public Payment() {}
	
	public Payment(Integer id, Double quantity, Integer month, Integer year, String concept, List<Player> players) {
		this.id = id;
		this.quantity = quantity;
		this.month = month;
		this.concept = concept;
		this.year = year;
		this.players = players;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
