package com.codespring2.Focuvax_Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.Lieu;
import com.codespring2.Focuvax_Back.repository.LieuRepository;

@Service
public class LieuService {
	@Autowired
	private LieuRepository repo;

	public List<Lieu> listAll() {
		return repo.findAll();
	}

	public void save(Lieu lieu) {
		repo.save(lieu);
	}

	public Lieu get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
