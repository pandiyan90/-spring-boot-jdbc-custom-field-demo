package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="cutom_field")
public class CustomField {

	@Column(name="cutom_field_id")
	private Long cutomFieldId;
	
	@Column(name="cutom_field_name")
	private String cutomFieldName;
	
	@JoinColumn(name="module_id")
	private Module module;

	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="is_active")
	private boolean isActive;

}
