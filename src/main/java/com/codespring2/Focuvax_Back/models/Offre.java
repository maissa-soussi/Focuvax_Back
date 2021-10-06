package com.codespring2.Focuvax_Back.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Offre {
	private Integer id;
	private String ref;
	private String titre;
	private String profil;
	private String mission;
	private String niveau_Exp;
	private String pays;

	private List<Candidat> candidats = new ArrayList<>();

	public Offre() {
		super();
	}

	public Offre(Integer id, String ref, String titre, String profil, String mission, String niveau_Exp, String pays,
			List<Candidat> candidats) {
		super();
		this.id = id;
		this.ref = ref;
		this.titre = titre;
		this.profil = profil;
		this.mission = mission;
		this.niveau_Exp = niveau_Exp;
		this.pays = pays;
		this.candidats = candidats;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getNiveau_Exp() {
		return niveau_Exp;
	}

	public void setNiveau_Exp(String niveau_Exp) {
		this.niveau_Exp = niveau_Exp;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@JsonIgnore
	@ManyToMany(targetEntity = Candidat.class, mappedBy = "offres", fetch = FetchType.EAGER)
	public List<Candidat> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

}
