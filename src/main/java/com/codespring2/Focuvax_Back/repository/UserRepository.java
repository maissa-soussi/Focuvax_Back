package com.codespring2.Focuvax_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespring2.Focuvax_Back.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	User findByRole(String role);
	User findByUsernameAndMdp(String username, String mdp);
	Boolean existsByUsername(String username);
}
