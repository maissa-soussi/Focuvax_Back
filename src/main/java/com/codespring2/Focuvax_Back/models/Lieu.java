package com.codespring2.Focuvax_Back.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lieu {
	private Integer id;
	private String valeur;
	
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
	public Lieu(Integer id, String valeur) {
		super();
		this.id = id;
		this.valeur = valeur;
	}
	public Lieu() {
	}
	
	
}
