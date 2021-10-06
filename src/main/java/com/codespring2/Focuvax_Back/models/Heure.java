package com.codespring2.Focuvax_Back.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Heure {
	private Integer id;
	private String valeur;

	private List<Entretien> entretiens = new ArrayList<>();

	public Heure() {
		super();
	}

	public Heure(Integer id, String valeur) {
		super();
		this.id = id;
		this.valeur = valeur;
	}

	public Heure(Integer id, String valeur, List<Entretien> entretiens) {
		super();
		this.id = id;
		this.valeur = valeur;
		this.entretiens = entretiens;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "heure")
	public List<Entretien> getEntretiens() {
		return entretiens;
	}

	public void setEntretiens(List<Entretien> entretiens) {
		this.entretiens = entretiens;
	}

}
