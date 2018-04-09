package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="custom_field_value")
public class CustomFieldValue {

	@Column(name="cutom_field_value_id")
	private Long cutomFieldValueId;
	
	@Column(name="cutom_field_value")
	private String cutomFieldValue;
	
	@JoinColumn(name="cutom_field_id")
	private CustomField customField;

	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="is_active")
	private boolean isActive;

}
