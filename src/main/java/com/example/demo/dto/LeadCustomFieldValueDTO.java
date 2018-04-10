package com.example.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LeadCustomFieldValueDTO {

	@NotNull
	private String customFieldName;

	private String customFieldValue;
	
	@NotNull
	private Long leadId;

}
