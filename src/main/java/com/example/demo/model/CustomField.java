package com.example.demo.model;

import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="custom_field", uniqueConstraints= {@UniqueConstraint(columnNames= {"custom_field_name"})})
public class CustomField {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="custom_field_id")
	private Long customFieldId;
	
	@Column(name="custom_field_name")
	private String customFieldName;
	
	@Column(name="created_on")
	@JsonIgnore
	private LocalDateTime createdOn;
	
	@Column(name="is_active")
	private Boolean isActive;

}
