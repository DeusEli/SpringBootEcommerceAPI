package com.practice.ecommerce.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.ecommerce.API.Entities.LoginBody;
import com.practice.ecommerce.API.Entities.RegistrationBody;
import com.practice.ecommerce.DAO.UserDAO;
import com.practice.ecommerce.Entities.User;
import com.practice.ecommerce.Exception.UserAlreadyExistsException;

@Service
public class UserService {

	private UserDAO userDAO;
	private EncryptionService encryptionService;
	private JWTService jwtService;

	// This
	public UserService(UserDAO userDAO, EncryptionService encryptionService, JWTService jwtService) {
		this.userDAO = userDAO;
		this.encryptionService = encryptionService;
		this.jwtService = jwtService;
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
		user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
		user.setPhone(registrationBody.getPhone());
		return userDAO.save(user);
	}

	public String loginUser(LoginBody loginBody) {
		Optional<User> opUser = userDAO.findByUsernameIgnoreCase(loginBody.getUsername());
		if (opUser.isPresent()) {
			if (encryptionService.checkPassword(loginBody.getPassword(), opUser.get().getPassword())) {
				return jwtService.generateJWT(opUser.get());
			}
		}
		return null;
	}
}
