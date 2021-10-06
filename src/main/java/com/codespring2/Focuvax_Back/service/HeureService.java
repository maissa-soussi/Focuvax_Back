package com.codespring2.Focuvax_Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.Heure;
import com.codespring2.Focuvax_Back.repository.HeureRepository;

@Service
public class HeureService {
	@Autowired
	private HeureRepository repo;

	public List<Heure> listAll() {
		return repo.findAll();
	}

	public void save(Heure heure) {
		repo.save(heure);
	}

	public Heure get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
