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

import com.codespring2.Focuvax_Back.models.Lieu;

@RestController
public class LieuController {
	@Autowired
	private LieuService service;
	
	@GetMapping("/lieux")
	public List<Lieu> list() {
		return service.listAll();
	}

	@GetMapping("/lieux/{id}")
	public ResponseEntity<Lieu> get(@PathVariable Integer id) {
		try {
			Lieu lieu = service.get(id);
			return new ResponseEntity<Lieu>(lieu, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Lieu>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/lieux")
	public void add(@RequestBody Lieu lieu) {
		service.save(lieu);
	}

	@DeleteMapping("/lieux/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
