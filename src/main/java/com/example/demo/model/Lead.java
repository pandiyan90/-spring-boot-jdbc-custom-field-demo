package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="lead", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "module_id"})} )
public class Lead {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lead_id", updatable=false, nullable=false)
	private Long leadId;
	
	@Column(name="title")
	private String title;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="lead_sorce")
	private String leadSource;
	
	@Column(name="lead_status")
	private boolean leadStatus;

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zipcode")
	private String zipcode;
	
	@Column(name="country")
	private String country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="module_id")
	@JsonIgnore
	private Module module;

	@Column(name="created_on")
	@JsonIgnore
	private LocalDateTime createdOn;
	
	@Column(name="is_active")
	private boolean isActive;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lead")
	private List<LeadCustomFieldValue> leadCustomFieldValue;

/*	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="lead_custom_field", joinColumns = @JoinColumn(name="lead_id"), inverseJoinColumns = @JoinColumn(name="custom_field_id"))
	private Set<CustomField> customFields;
*/
}
