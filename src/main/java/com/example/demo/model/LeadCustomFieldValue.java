package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="lead_custom_field_value", uniqueConstraints = {@UniqueConstraint(columnNames= {"lead_custom_field_name", "lead_id"})})
public class LeadCustomFieldValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lead_custom_field_value_id")
	private Long leadCustomFieldId;
	
	@Column(name="lead_custom_field_name")
	private String leadCustomFieldName;

	@Column(name="lead_custom_field_value")
	private String leadCustomFieldValue;
	
	@Column(name="created_on")
	@JsonIgnore
	private LocalDateTime createdOn;
	
	@Column(name="is_active")
	private Boolean isActive;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="lead_id")
	@JsonIgnore
	private Lead lead;

}
