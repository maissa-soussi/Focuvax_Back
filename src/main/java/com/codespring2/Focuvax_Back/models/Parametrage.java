package com.codespring2.Focuvax_Back.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parametrage {
	private Integer id;
	private String ecole;
	private String diplome;
	private String specialite;
	private String nb_annee_exp;
	private String etat;
	private String pays;
	private Integer nb_Offre;

	public Parametrage() {
	}

	public Parametrage(Integer id, String ecole, String diplome, String specialite, String nb_annee_exp, String etat,
			String pays, Integer nb_Offre) {
		this.id = id;
		this.ecole = ecole;
		this.diplome = diplome;
		this.specialite = specialite;
		this.nb_annee_exp = nb_annee_exp;
		this.etat = etat;
		this.pays = pays;
		this.nb_Offre = nb_Offre;
	}

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(columnDefinition = "TEXT")
	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	@Column(columnDefinition = "TEXT")
	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	@Column(columnDefinition = "TEXT")
	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	@Column(columnDefinition = "TEXT")
	public String getNb_annee_exp() {
		return nb_annee_exp;
	}

	public void setNb_annee_exp(String nb_annee_exp) {
		this.nb_annee_exp = nb_annee_exp;
	}

	@Column(columnDefinition = "TEXT")
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Integer getNb_Offre() {
		return nb_Offre;
	}

	public void setNb_Offre(Integer nb_Offre) {
		this.nb_Offre = nb_Offre;
	}

}
