package com.codespring2.Focuvax_Back.service;

import java.util.ArrayList;
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

import com.codespring2.Focuvax_Back.models.Candidat;
import com.codespring2.Focuvax_Back.models.Offre;

@RestController
public class OffreController {
	@Autowired
	private OffreService service;
	
	@Autowired
	private CandidatService candidatservice;

	@GetMapping("/offres")
	public List<Offre> list() {
		return service.listAll();
	}

	@GetMapping("/offres/nb")
	public Integer nbre() {
		return service.listAll().size();
	}

	@GetMapping("/offres/{id}")
	public ResponseEntity<Offre> get(@PathVariable Integer id) {
		try {
			Offre offre = service.get(id);
			return new ResponseEntity<Offre>(offre, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Offre>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/offresStat")
	public List<Integer> getStat() {
		List<Candidat> c = candidatservice.listAll();
		List<Integer> l = new ArrayList<Integer>();
		List<Offre> offres = service.listAll();
		int i=0;
		int nb=0;
		while(i< offres.size()) {
			int j=0;
			while (j<c.size()) {
				List<Offre> offrecandidat = c.get(j).getOffres();
				for(int k=0; k<offrecandidat.size(); k++) {
					if(offrecandidat.get(k).getId()==offres.get(i).getId())
						nb++;
				}
				j++;
			}
			l.add(nb);
			i++;
			nb=0;
		}
		
		
		
		return l;

	}

	@PostMapping("/offres")
	public void add(@RequestBody Offre offre) {
		Offre u = service.findByRef(offre.getRef());
		if (u == null)
			service.save(offre);
	}

	@PutMapping("/offres/{id}")
	public ResponseEntity<?> update(@RequestBody Offre offre, @PathVariable Integer id) {
		try {
			Offre existOffre = service.get(id);
			service.save(offre);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/offres/{id}")
	public void delete(@PathVariable Integer id) {
		List<Candidat> c = candidatservice.listAll();
		for(int i=0; i<c.size();i++) {
			c.get(i).deleteOffre(id);
		}
		service.delete(id);
	}
}
