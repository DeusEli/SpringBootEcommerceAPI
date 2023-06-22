package com.practice.ecommerce.API.Controllers.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecommerce.API.Entities.LoginBody;
import com.practice.ecommerce.API.Entities.LoginResponse;
import com.practice.ecommerce.API.Entities.RegistrationBody;
import com.practice.ecommerce.Exception.UserAlreadyExistsException;
import com.practice.ecommerce.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private UserService userService;

	// Constructor Injection
	private AuthenticationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	/*
	 * ResponseEntity is used to send the response with status code.
	 * 
	 * @Valid is used to validate the request body with the constraints defined
	 * in the RegistrationBody class.
	 */
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
		try {
			userService.registerUser(registrationBody);
			// This will return a status code 200
			return ResponseEntity.ok().build();
		} catch (UserAlreadyExistsException e) {
			// This will return the status code 409
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
		String jwt = userService.loginUser(loginBody);
		if (jwt == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setJwt(jwt);
			// This will return a JSON with the JWT
			return ResponseEntity.ok(loginResponse);
		}
	}
}
