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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codespring2.Focuvax_Back.models.Heure;

@RestController
public class HeureController {
	@Autowired
	private HeureService service;

	@GetMapping("/heures")
	public List<Heure> list() {
		return service.listAll();
	}

	@GetMapping("/heures/{id}")
	public ResponseEntity<Heure> get(@PathVariable Integer id) {
		try {
			Heure heure = service.get(id);
			return new ResponseEntity<Heure>(heure, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Heure>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/heures")
	public void add(@RequestBody Heure heure) {
		service.save(heure);
	}

	@DeleteMapping("/heures/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
