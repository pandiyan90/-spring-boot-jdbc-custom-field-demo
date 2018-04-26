package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="module")
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="module_id")
	private Long moduleId;
	
	@Column(name="module_name", unique = true)
	private String moduleName;
	
	@Column(name="created_on")
	@JsonIgnore
	private LocalDateTime createdOn;
	
	@Column(name="is_active")
	private Boolean isActive;

/*	@OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
	private List<Lead> lead;
*/
}
