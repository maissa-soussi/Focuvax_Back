package com.codespring2.Focuvax_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespring2.Focuvax_Back.models.Entretien;

public interface EntretienRepository extends JpaRepository<Entretien, Integer>{
	Entretien findByDate(String date);
}
