package com.codespring2.Focuvax_Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.Candidat;
import com.codespring2.Focuvax_Back.repository.CandidatRepository;

@Service
public class CandidatService {
	@Autowired
	private CandidatRepository repo;

	public List<Candidat> listAll() {
		return repo.findAll();
	}

	public void save(Candidat candidat) {
		repo.save(candidat);
	}

	public Candidat get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
