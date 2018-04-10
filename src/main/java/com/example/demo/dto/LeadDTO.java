package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LeadDTO {
	
	private String title;

	private String firstName;
	
	private String lastName;
	
	@NotNull
	@Email(message="Please enter a valid email id")
	private String email;
	
	private String mobile;
	
	private String leadSource;
	
	private boolean leadStatus;

	private String address;

	private String city;
	
	private String state;
	
	private String zipcode;
	
	private String country;

	private Long moduleId;

}
