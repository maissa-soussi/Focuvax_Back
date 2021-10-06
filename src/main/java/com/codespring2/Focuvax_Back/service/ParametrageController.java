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

import com.codespring2.Focuvax_Back.models.Parametrage;

@RestController
public class ParametrageController {
	@Autowired
	private ParametrageService service;

	@GetMapping("/parametrages")
	public List<Parametrage> list() {
		return service.listAll();
	}

	@GetMapping("/parametrages/{id}")
	public ResponseEntity<Parametrage> get(@PathVariable Integer id) {
		try {
			Parametrage parametrage = service.get(id);
			return new ResponseEntity<Parametrage>(parametrage, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Parametrage>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/parametrages")
	public void add(@RequestBody Parametrage parametrage) {
		service.save(parametrage);
	}

	@PostMapping("/parametrages/diplome")
	public void addDiplome(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setDiplome(parametrage.getDiplome());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setDiplome(parametrage.getDiplome());
			service.save(p1);
		}

	}

	@PostMapping("/parametrages/ecole")
	public void addEcole(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setEcole(parametrage.getEcole());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setEcole(parametrage.getEcole());
			service.save(p1);
		}

	}

	@PostMapping("/parametrages/etat")
	public void addEtat(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setEtat(parametrage.getEtat());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setEtat(parametrage.getEtat());
			service.save(p1);
		}

	}

	@PostMapping("/parametrages/exp")
	public void addExp(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setNb_annee_exp(parametrage.getNb_annee_exp());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setNb_annee_exp(parametrage.getNb_annee_exp());
			service.save(p1);
		}

	}

	@PostMapping("/parametrages/pays")
	public void addPays(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setPays(parametrage.getPays());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setPays(parametrage.getPays());
			service.save(p1);
		}

	}

	@PostMapping("/parametrages/specialite")
	public void addSpecialite(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setSpecialite(parametrage.getSpecialite());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setSpecialite(parametrage.getSpecialite());
			service.save(p1);
		}

	}

	@PostMapping("/parametrages/nbOffres")
	public void addNbOffre(@RequestBody Parametrage parametrage) {
		try {
			Parametrage p = service.get(1);
			p.setNb_Offre(parametrage.getNb_Offre());
			service.save(p);
		} catch (NoSuchElementException e) {
			Parametrage p1 = new Parametrage();
			p1.setId(1);
			p1.setNb_Offre(parametrage.getNb_Offre());
			service.save(p1);
		}

	}

	@DeleteMapping("/parametrages/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
