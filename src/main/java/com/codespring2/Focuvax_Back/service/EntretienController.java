package com.codespring2.Focuvax_Back.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codespring2.Focuvax_Back.models.Entretien;
import com.codespring2.Focuvax_Back.models.Heure;

@RestController
public class EntretienController {
	@Autowired
	private EntretienService service;
	
	@Autowired
	private HeureService heureservice;

	@GetMapping("/entretiens")
	public List<Entretien> list() {
		return service.listAll();
	}
	
	@GetMapping("/entretiens/nb")
	public Integer nbre() {
		return service.listAll().size();
	}

	@GetMapping("/entretiens/{id}")
	public ResponseEntity<Entretien> get(@PathVariable Integer id) {
		try {
			Entretien entretien = service.get(id);
			return new ResponseEntity<Entretien>(entretien, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Entretien>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/entretiens/{heureId}")
	public Integer add(@RequestBody Entretien entretien,  @PathVariable Integer heureId) {
			Heure h= heureservice.get(heureId);
			Entretien existEntretien = service.findByDateAndHeure(entretien.getDate(), h.getValeur());
			if(existEntretien.getId() != null)
			{	
				return existEntretien.getId();
			}
			else {
				
			service.save(entretien);
			Heure heure = heureservice.get(heureId);
			entretien.assignHeure(heure);
			service.save(entretien);
			return entretien.getId();
			}
		
		
	}

	@PutMapping("/entretiens/{id}")
	public ResponseEntity<?> update(@RequestBody Entretien entretien, @PathVariable Integer id) {
		try {
			Entretien existEntretien = service.get(id);
			service.save(entretien);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/entretiens/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/entretiens/heures/{heureId}")
	void assignHeureToEntretien(@RequestBody Entretien e, @PathVariable Integer heureId) {
		Entretien entretien = service.get(e.getId());
		Heure heure = heureservice.get(heureId);
			entretien.assignHeure(heure);
			service.save(entretien);
	}
	
}
