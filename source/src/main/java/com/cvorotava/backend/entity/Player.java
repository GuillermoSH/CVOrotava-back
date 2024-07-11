package com.cvorotava.backend.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
public class Player implements Serializable {
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

	public Player() {
	}

	public Player(Integer id, String dni, String name, String surname1, String surname2, Integer telephone, String email,
			String address, String birthday, String category) {
		this.id = id;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}