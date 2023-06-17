package com.practice.ecommerce.DAO;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.practice.ecommerce.Entities.User;

public interface UserDAO extends CrudRepository<User, Long> {

	// The way these methods are named is important because Spring Data JPA will use
	// them to create the queries automatically
	// Spring Data JPA will create a query that looks like this:
	// SELECT * FROM user WHERE username = ?
	Optional<User> findByUsernameIgnoreCase(String username);

	Optional<User> findByEmailIgnoreCase(String email);

	Optional<User> findByPhone(String phone);
}
