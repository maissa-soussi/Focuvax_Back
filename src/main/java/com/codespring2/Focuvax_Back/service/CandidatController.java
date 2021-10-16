package com.codespring2.Focuvax_Back.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codespring2.Focuvax_Back.models.Candidat;
import com.codespring2.Focuvax_Back.models.Entretien;
import com.codespring2.Focuvax_Back.models.Heure;
import com.codespring2.Focuvax_Back.models.Lieu;
import com.codespring2.Focuvax_Back.models.Offre;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CandidatController {
	@Autowired
	private CandidatService service;
	@Autowired
	private OffreService offreservice;
	@Autowired
	private EntretienService entretienservice;
	@Autowired
	private HeureService heureservice;
	@Autowired
	private LieuService lieuservice;

	@Autowired
	ServletContext context;

	@Autowired
	private EmailSenderService mailservice;

	@GetMapping("/candidats")
	public List<Candidat> list() {
		return service.listAll();
	}

	@PostMapping("/candidats/EmailSend/{etat}/{objet}")
	public void mailSend(@PathVariable String etat,@PathVariable String objet, @RequestBody String mailbody) {
		List<Candidat> l = service.listAll();
		int i=0;
		String mailEnvoie="";

		if(!l.isEmpty())
		{while (i<l.size()) {
		  if(etat.equals(l.get(i).getEtat()) && l.get(i).getMailenvoye()=="non"){
			  String candidatdate = l.get(i).getEntretien().getDate();
			  String date = candidatdate.substring(8, 10)+"-"+candidatdate.substring(5, 7)+"-"+candidatdate.substring(0, 4);
			  mailEnvoie=mailbody;
			  mailEnvoie=mailEnvoie.replaceAll("<nom>", l.get(i).getUser().getPrenom());
			  mailEnvoie=mailEnvoie.replaceAll("<date>", date);
			  mailEnvoie=mailEnvoie.replaceAll("<heure>", l.get(i).getEntretien().getHeure());
			  mailEnvoie=mailEnvoie.replaceAll("<lieu>", l.get(i).getLieu());
			mailservice.sendSimpleEmail(l.get(i).getUser().getUsername(), mailEnvoie, objet);
			l.get(i).setMailenvoye("oui");
		  }
			i++;
		}
		
		}
	}

	@GetMapping("/candidats/nb")
	public Integer nbre() {
		return service.listAll().size();
	}

	@GetMapping("/candidats/entretiens/nb")
	public Integer nbreEntretiens() {
		int nb = 0;
		int i = 0;
		List<Candidat> l = service.listAll();
		while (i < l.size()) {
			if (l.get(i).getEntretien() == null) {
			} else
				nb++;

			i++;
		}
		return nb;
	}

	@GetMapping("/candidats/noEntretiens/nb")
	public Integer nbreNoEntretiens() {
		int nb = 0;
		int i = 0;
		List<Candidat> l = service.listAll();
		while (i < l.size()) {
			if (l.get(i).getEntretien() == null) {
				nb++;
			}

			i++;
		}
		return nb;
	}

	@GetMapping("/candidats/{id}")
	public ResponseEntity<Candidat> get(@PathVariable Integer id) {
		try {
			Candidat candidat = service.get(id);
			return new ResponseEntity<Candidat>(candidat, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Candidat>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/candidats/user/{username}")
	public ResponseEntity<Candidat> getCandidat(@PathVariable String username) {
		try {

			List<Candidat> l = service.listAll();
			int ok = 0;
			int i = 0;
			if (!l.isEmpty()) {
				while (i < l.size() && ok == 0) {
					if (username.equals(l.get(i).getUser().getUsername())) {

						ok = 1;
					}
					i++;
				}
			}
			if (ok == 1)
				return new ResponseEntity<Candidat>(l.get(i - 1), HttpStatus.OK);
			else
				return new ResponseEntity<Candidat>(new Candidat(), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Candidat>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/candidats")
	public void add(@RequestParam("candidat") String ca, @RequestParam("cv") MultipartFile cv,
			@RequestParam("photo") MultipartFile photo) throws JsonMappingException, JsonProcessingException {

		Candidat candidat = new ObjectMapper().readValue(ca, Candidat.class);
		boolean isExist = new File(context.getRealPath("/Cv/")).exists();
		boolean isExist2 = new File(context.getRealPath("/Photo/")).exists();
		if (!isExist) {
			new File(context.getRealPath("/Cv/")).mkdir();
		}

		if (!isExist2) {
			new File(context.getRealPath("/Photo/")).mkdir();
		}

		String cvname = cv.getOriginalFilename();
		String newCvname = FilenameUtils.getBaseName(cvname) + "." + FilenameUtils.getExtension(cvname);
		File servercv = new File(context.getRealPath("/Cv/" + File.separator + newCvname));
		
		String photoname = photo.getOriginalFilename();
		String newPhotoname = FilenameUtils.getBaseName(photoname) + "." + FilenameUtils.getExtension(photoname);
		File serverphoto = new File(context.getRealPath("/Photo/" + File.separator + newPhotoname));
		try {
			FileUtils.writeByteArrayToFile(servercv, cv.getBytes());
			FileUtils.writeByteArrayToFile(serverphoto, photo.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		candidat.setCv(newCvname);
		candidat.setPhoto(newPhotoname);
		candidat.setMailenvoye("non");
		service.save(candidat);
	}
	
	@PostMapping("/candidats/SansPhoto")
	public void addSansPhoto(@RequestParam("candidat") String ca, @RequestParam("cv") MultipartFile cv) throws JsonMappingException, JsonProcessingException {

		Candidat candidat = new ObjectMapper().readValue(ca, Candidat.class);
		boolean isExist = new File(context.getRealPath("/Cv/")).exists();
		if (!isExist) {
			new File(context.getRealPath("/Cv/")).mkdir();
		}

		String cvname = cv.getOriginalFilename();
		String newCvname = FilenameUtils.getBaseName(cvname) + "." + FilenameUtils.getExtension(cvname);
		File servercv = new File(context.getRealPath("/Cv/" + File.separator + newCvname));
		
		try {
			FileUtils.writeByteArrayToFile(servercv, cv.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		candidat.setCv(newCvname);
		candidat.setMailenvoye("non");
		service.save(candidat);
	}

	@GetMapping("/candidats/cv/{id}")
	public byte[] getCv(@PathVariable Integer id) throws Exception {
		Candidat candidat = service.get(id);
		return Files.readAllBytes(Paths.get(context.getRealPath("/Cv/") + candidat.getCv()));
	}

	@GetMapping("/candidats/photo/{id}")
	public byte[] getPhoto(@PathVariable Integer id) throws Exception {
		Candidat candidat = service.get(id);
		return Files.readAllBytes(Paths.get(context.getRealPath("/Photo/") + candidat.getPhoto()));
	}

	
	@PostMapping("/candidats/postulation/{date}")
	public void addDatePost(@RequestBody Candidat candidat, @PathVariable String date) {
		Candidat c = service.get(candidat.getId());
		c.setDate_postulation(date);
		service.save(c);
	}

	@DeleteMapping("/candidats/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@PutMapping("/candidats/{candidatId}/offres")
	void enrollOffreToCandidat(@PathVariable Integer candidatId, @RequestBody Integer[] offreId) {
		Candidat candidat = service.get(candidatId);
		
		List<Offre> offres = candidat.getOffres();
		int i = 0;
		int ok = 0;
		for (int j=0; j<offreId.length; j++)
		{
		Offre offre = offreservice.get(offreId[j]);
		if (!offres.isEmpty()) {
			while (i < offres.size() && ok == 0) {
				if (offres.get(i).getId() == offreId[j]) {
					ok = 1;
				}
				i++;
			}

			if (ok == 0) {
				candidat.enrollOffre(offre);
				service.save(candidat);
			}
		} else {
			candidat.enrollOffre(offre);
			service.save(candidat);
		}}
	}

	@PutMapping("/candidats/entretiens/{entretienId}/{etat}")
	void assignEntretienToCandidat(@RequestBody Candidat c, @PathVariable Integer entretienId,
			@PathVariable String etat) {
		Candidat candidat = service.get(c.getId());
		Entretien entretien = entretienservice.get(entretienId);
		candidat.assignEntretien(entretien);
		candidat.setEtat(etat);
		service.save(candidat);
	}

	@PutMapping("/candidats/etatPut/{etat}")
	void etatPut(@RequestBody Candidat c, @PathVariable String etat) {
		Candidat candidat = service.get(c.getId());
		candidat.setEtat(etat);
		service.save(candidat);
	}
	
	@PutMapping("/candidats/lieuPut/{lieu}")
	void lieuPut(@RequestBody Candidat c, @PathVariable String lieu) {
		Candidat candidat = service.get(c.getId());
		candidat.setLieu(lieu);
		service.save(candidat);
	}

	@GetMapping("/candidats/offres/{ref}")
	public List<Candidat> list(@PathVariable String ref) {
		List<Candidat> li = service.listAll();
		List<Candidat> res = new ArrayList<Candidat>();
		if (!li.isEmpty()) {
			int i = 0;
			while (i < li.size()) {
				if (!li.get(i).getOffres().isEmpty()) {
					int j = 0;
					int ok = 0;
					while (j < li.get(i).getOffres().size() && ok == 0) {
						if (ref.equals(li.get(i).getOffres().get(j).getRef())) {
							res.add(li.get(i));
							ok = 1;
						}
						j++;
					}
				}
				i++;
			}
		}

		return res;
	}

	@GetMapping("/candidats/nbentretiens/dateHourNumber/{dateValue}/{heureValue}/{lieuValue}")
	public Integer nbEntretienParHeure(@PathVariable String dateValue, @PathVariable Integer heureValue, @PathVariable String lieuValue) {
		List<Candidat> l = service.listAll();
		int i = 0;
		int num = 0;
		Heure hValue = heureservice.get(heureValue);
		if(hValue != null)
		{
		while (i < l.size()) {
			Entretien e = l.get(i).getEntretien();
			String lieu = l.get(i).getLieu();
			if(e != null && lieu != "") {
			String d = e.getDate();
			String h = e.getHeure();

			if (d.equals(dateValue) && h.equals(hValue.getValeur()) && lieu.equals(lieuValue))
				num++;
			}

			i++;
		}}
		return num;
	}

}
