package com.codespring2.Focuvax_Back.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Candidat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tel;
	private String ecole;
	private String diplome;
	private String annee_Diplome;
	private String specialite;
	private String nb_annee_experience;
	private String photo;
	private String cv;
	private String linkedin;
	private String etat;
	private String date_postulation;
	private String mailenvoye;
	@OneToOne
	private Lieu lieu;
	@OneToOne
	private User user;

	@ManyToOne
	private Entretien entretien;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "candidats_offres", joinColumns = @JoinColumn(name = "candidat_id"), inverseJoinColumns = @JoinColumn(name = "offre_id"))
	private List<Offre> offres = new ArrayList<>();

	public Candidat() {
		super();
	}

	public Candidat(Integer id, String tel, String ecole, String diplome, String annee_Diplome, String specialite,
			String nb_annee_experience, String photo, String cv, String linkedin, String etat, String date_postulation,
			String mailenvoye, Lieu lieu, User user, Entretien entretien, List<Offre> offres) {
		super();
		this.id = id;
		this.tel = tel;
		this.ecole = ecole;
		this.diplome = diplome;
		this.annee_Diplome = annee_Diplome;
		this.specialite = specialite;
		this.nb_annee_experience = nb_annee_experience;
		this.photo = photo;
		this.cv = cv;
		this.linkedin = linkedin;
		this.etat = etat;
		this.date_postulation = date_postulation;
		this.mailenvoye = mailenvoye;
		this.lieu = lieu;
		this.user = user;
		this.entretien = entretien;
		this.offres = offres;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public String getAnnee_Diplome() {
		return annee_Diplome;
	}

	public void setAnnee_Diplome(String annee_Diplome) {
		this.annee_Diplome = annee_Diplome;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getNb_annee_experience() {
		return nb_annee_experience;
	}

	public void setNb_annee_experience(String nb_annee_experience) {
		this.nb_annee_experience = nb_annee_experience;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getMailenvoye() {
		return mailenvoye;
	}

	public void setMailenvoye(String mailenvoye) {
		this.mailenvoye = mailenvoye;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Entretien getEntretien() {
		return entretien;
	}

	public void setEntretien(Entretien entretien) {
		this.entretien = entretien;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public void enrollOffre(Offre offre) {
		offres.add(offre);
	}

	public void assignEntretien(Entretien entretien) {
		this.entretien = entretien;
	}

	public String getDate_postulation() {
		return date_postulation;
	}

	public void setDate_postulation(String date_postulation) {
		this.date_postulation = date_postulation;
	}

}
