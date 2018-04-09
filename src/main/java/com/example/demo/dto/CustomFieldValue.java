package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomFieldValue {

	private Long cutomFieldValueId;
	
	private String cutomFieldValue;
	
	private CustomField customField;

}
