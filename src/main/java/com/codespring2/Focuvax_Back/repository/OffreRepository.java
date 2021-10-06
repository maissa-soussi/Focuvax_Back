package com.codespring2.Focuvax_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespring2.Focuvax_Back.models.Offre;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
	Offre findByRef(String ref);
}
