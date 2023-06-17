package com.practice.ecommerce.API.Entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class RegistrationBody {

	@NotBlank
	@Size(min = 3, max = 30)
	private String username;

	@NotBlank
	@Email
	@Size(min = 5, max = 100)
	private String email;

	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
	@Size(min = 8, max = 30)
	private String password;

	@NotBlank
	@Size(min = 2, max = 30)
	private String name;

	@NotBlank
	@Size(min = 2, max = 30)
	private String lastname;

	@NotBlank
	@Size(min = 8, max = 10)
	private String phone;
}
