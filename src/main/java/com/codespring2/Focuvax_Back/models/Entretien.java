package com.codespring2.Focuvax_Back.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Entretien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String date;

	@ManyToOne
	private Heure heure;

	@JsonIgnore
	@OneToMany(mappedBy = "entretien")
	private List<Candidat> candidats = new ArrayList<>();

	public Entretien(Integer id, String date, Heure heure) {
		super();
		this.id = id;
		this.date = date;
		this.heure = heure;
	}

	public Entretien() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Heure getHeure() {
		return heure;
	}

	public void setHeure(Heure heure) {
		this.heure = heure;
	}

	public void assignHeure(Heure heure) {
		this.heure = heure;
	}

	public List<Candidat> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

	@Override
	public String toString() {
		return "Entretien [id=" + id + ", date=" + date + ", heure=" + heure + ", candidats=" + candidats + "]";
	}

}
