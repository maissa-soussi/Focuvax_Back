package com.codespring2.Focuvax_Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.Entretien;
import com.codespring2.Focuvax_Back.repository.EntretienRepository;

@Service
public class EntretienService {
	@Autowired
	private EntretienRepository repo;

	public List<Entretien> listAll() {
		return repo.findAll();
	}

	public void save(Entretien entretien) {
		repo.save(entretien);
	}

	public Entretien get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Entretien findByDate(String date) {
		Entretien en = repo.findByDate(date);
		return en;
	}

	public Entretien findByDateAndHeure(String date, Integer idheure) {
		List<Entretien> li = repo.findAll();
		int i = 0;
		Entretien res = new Entretien();
		while (i < li.size()) {
			if (date.equals(li.get(i).getDate()) && li.get(i).getHeure().getId() == idheure) {
				return li.get(i);
			} else
				i++;
		}

		return res;

	}

}
