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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codespring2.Focuvax_Back.models.User;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public List<User> list() {
		return service.listAll();
	}

	@GetMapping("/users/admins")
	public List<User> admins() {
		List<User> li = service.listAll();
		List<User> l = new ArrayList<User>();
		for (int i = 0; i < li.size(); i++) {
			if (!("ROLE_CANDIDAT".equals(li.get(i).getRole()))) {
				l.add(li.get(i));
			}
		}
		return l;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id) {
		try {
			User user = service.get(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/myUser/{username}")
	public ResponseEntity<User> get(@PathVariable String username) {
		try {
			User user = service.findByUsername(username);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/users/save")
	public ResponseEntity<?> add(@RequestBody User user) {
		User existUser = service.findByUsername(user.getUsername());
		if (existUser == null) {
			service.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
