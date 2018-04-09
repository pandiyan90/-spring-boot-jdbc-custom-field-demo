package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="module")
public class Module {
	
	@Column(name="module_id")
	private Long moduleId;
	
	@Column(name="module_name")
	private String moduleName;
	
	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="is_active")
	private boolean isActive;

}
