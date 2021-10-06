package com.codespring2.Focuvax_Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.Offre;
import com.codespring2.Focuvax_Back.repository.OffreRepository;

@Service
public class OffreService {
	@Autowired
	private OffreRepository repo;

	public List<Offre> listAll() {
		return repo.findAll();
	}

	public void save(Offre offre) {
		repo.save(offre);
	}

	public Offre get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Offre findByRef(String ref) {
		Offre offre = repo.findByRef(ref);
		return offre;
	}
}
