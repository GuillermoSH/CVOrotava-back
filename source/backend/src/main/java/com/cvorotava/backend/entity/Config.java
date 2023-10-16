package com.cvorotava.backend.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Config.findAll", query = "SELECT c FROM Config c")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String notification_emails;
	private Integer benjamin_year;
	private Integer alevin_year;
	private Integer infantil_year;
	private Integer cadete_year;
	private Integer juvenil_year;
	private Integer junior_year;
	private String mod_date;

	public Config() {

	}

	public Config(Integer id, String notification_emails,Integer benjamin_year, Integer alevin_year,
			Integer infantil_year, Integer cadete_year, Integer juvenil_year, Integer junior_year, String mod_date) {
		this.id = id;
		this.notification_emails = notification_emails;
		this.benjamin_year = benjamin_year;
		this.alevin_year = alevin_year;
		this.infantil_year = infantil_year;
		this.cadete_year = cadete_year;
		this.juvenil_year = juvenil_year;
		this.junior_year = junior_year;
		this.mod_date = mod_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotification_emails() {
		return notification_emails;
	}

	public void setNotification_emails(String notification_emails) {
		this.notification_emails = notification_emails;
	}

	public Integer getBenjamin_year() {
		return benjamin_year;
	}

	public void setBenjamin_year(Integer benjamin_year) {
		this.benjamin_year = benjamin_year;
	}

	public Integer getAlevin_year() {
		return alevin_year;
	}

	public void setAlevin_year(Integer alevin_year) {
		this.alevin_year = alevin_year;
	}

	public Integer getInfantil_year() {
		return infantil_year;
	}

	public void setInfantil_year(Integer infantil_year) {
		this.infantil_year = infantil_year;
	}

	public Integer getCadete_year() {
		return cadete_year;
	}

	public void setCadete_year(Integer cadete_year) {
		this.cadete_year = cadete_year;
	}

	public Integer getJuvenil_year() {
		return juvenil_year;
	}

	public void setJuvenil_year(Integer juvenil_year) {
		this.juvenil_year = juvenil_year;
	}

	public Integer getJunior_year() {
		return junior_year;
	}

	public void setJunior_year(Integer junior_year) {
		this.junior_year = junior_year;
	}

	public String getMod_date() {
		return mod_date;
	}

	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
}
