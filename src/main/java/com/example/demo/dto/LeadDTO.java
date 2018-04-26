package com.example.demo.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.demo.model.Module;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	private String customField1;

	private String customField2;

	private String customField3;

	private String customField4;

	private String customField5;

	private String customField6;

	private boolean isActive;

}
