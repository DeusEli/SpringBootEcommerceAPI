package com.practice.ecommerce.API.Entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginBody {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
