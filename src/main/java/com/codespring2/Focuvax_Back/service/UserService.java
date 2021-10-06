package com.codespring2.Focuvax_Back.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codespring2.Focuvax_Back.models.User;
import com.codespring2.Focuvax_Back.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repo;

	private final PasswordEncoder passwordEncoder;

	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> listAll() {
		return repo.findAll();
	}

	public void save(User user) {
		user.setMdp(passwordEncoder.encode(user.getMdp()));
		repo.save(user);
	}

	public User get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public User findByUsername(String username) {
		User u = repo.findByUsername(username);
		return u;
	}

	public User findByRole(String role) {
		User u = repo.findByUsername(role);
		return u;
	}

	public User findByUsernameAndMdp(String username, String mdp) {
		User u = repo.findByUsernameAndMdp(username, mdp);
		return u;
	}

	public User Login(User user) {
		User u = repo.findByUsernameAndMdp(user.getUsername(), user.getMdp());
		if (u != null) {
			return u;
		} else {
			return null;
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = repo.findByUsername(username);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(u.getRole()));
		return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getMdp(), authorities);
	}

}
