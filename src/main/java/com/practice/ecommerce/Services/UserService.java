package com.practice.ecommerce.Services;

import org.springframework.stereotype.Service;

import com.practice.ecommerce.API.Entities.RegistrationBody;
import com.practice.ecommerce.DAO.UserDAO;
import com.practice.ecommerce.Entities.User;
import com.practice.ecommerce.Exception.UserAlreadyExistsException;

@Service
public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
		// This checks if the username, email or phone already exists in the database
		if (userDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()
				|| userDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
				|| userDAO.findByPhone(registrationBody.getPhone()).isPresent()) {
			throw new UserAlreadyExistsException();
		}
		User user = new User();
		user.setUsername(registrationBody.getUsername());
		user.setEmail(registrationBody.getEmail());
		user.setName(registrationBody.getName());
		user.setLastname(registrationBody.getLastname());
		// TODO: Hash password
		user.setPassword(registrationBody.getPassword());
		user.setPhone(registrationBody.getPhone());
		return userDAO.save(user);
	}
}
