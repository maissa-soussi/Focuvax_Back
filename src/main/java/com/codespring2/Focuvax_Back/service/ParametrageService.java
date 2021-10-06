package com.codespring2.Focuvax_Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.Parametrage;
import com.codespring2.Focuvax_Back.repository.ParametrageRepository;

@Service
public class ParametrageService {
	@Autowired
	private ParametrageRepository repo;

	public List<Parametrage> listAll() {
		return repo.findAll();
	}

	public void save(Parametrage parametrage) {
		repo.save(parametrage);
	}

	public Parametrage get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
