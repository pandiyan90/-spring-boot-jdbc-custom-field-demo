package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomFieldPropertzDTO {
	
	private Long propertyId;
	
	private String module;

	private String customFieldName;

	private String customFieldType;

	private String customFieldValue;

	private String customFieldMinLength;

	private String customFieldMaxLength;

	private String customFieldPlaceholder;

	private String customFieldSize;

	private String customFieldDisabled;

	private String customFieldAutofocus;

	private String customFieldChecked;

	private String customFieldRequired;

	private String isActive;

}
