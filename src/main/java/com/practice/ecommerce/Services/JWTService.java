package com.practice.ecommerce.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.practice.ecommerce.Entities.User;

import jakarta.annotation.PostConstruct;

@Service
public class JWTService {

	/*
	 * @Value is used to get the value of a property from the application.properties
	 * file.
	 */

	// The algorithmKey is the key that will be used to encrypt the token
	@Value("${jwt.algorithm.key}")
	private String algorithmKey;

	// The issuer is the entity that issues the token (ecommerce in this case)
	@Value("${jwt.issuer}")
	private String issuer;

	// The expiryInSeconds is the time in seconds that the token will be valid for
	@Value("${jwt.expiryInSeconds}")
	private int expiryInSeconds;

	private Algorithm algorithm;
	/*
	 * The USERNAME_KEY is the key that will be used to store the username in the
	 * JWT payload.
	 */
	private static final String USERNAME_KEY = "USERNAME";

	/*
	 * The @PostConstruct annotation is used to indicate that the method should be
	 * called after the bean has been initialized.
	 * 
	 * If this annotation is not used, the algorithm will be null because the
	 * algorithmKey will be null when the bean is initialized
	 */
	@PostConstruct
	public void postConstruct() {
		algorithm = Algorithm.HMAC256(algorithmKey);
	}

	/*
	 * This method generates a JWT token using the user's username as the payload
	 * and the algorithmKey as the key to encrypt the token with the HMAC256
	 * algorithm.
	 */
	public String generateJWT(User user) {
		return JWT.create()
				.withClaim(USERNAME_KEY, user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
				.withIssuer(issuer)
				.sign(algorithm);
	}
}
