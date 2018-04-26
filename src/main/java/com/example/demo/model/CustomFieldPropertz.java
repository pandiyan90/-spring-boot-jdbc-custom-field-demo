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
import javax.validation.Constraint;
import javax.validation.constraints.Email;

import com.example.demo.dto.Custom;
import com.example.demo.dto.Label;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="custom_field_propertz", uniqueConstraints = {@UniqueConstraint(columnNames = {"module_id","custom_field_name"})})
public class CustomFieldPropertz {
	
	public CustomFieldPropertz(String customFieldName, Module module){
		this.customFieldName = customFieldName;
		this.module = module;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="property_id")
	private Long propertyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="module_id")
	@JsonIgnore
	private Module module;

	@Column(name="custom_field_name")
	@Label
	private String customFieldName;

	@Column(name="custom_field_label")
	@Label
	private String customFieldLabel;

	@Column(name="custom_field_type")
	private String customFieldType;

	@Column(name="custom_field_value")
	private String customFieldValue;

	@Column(name="custom_field_min_length")
	private String customFieldMinLength;

	@Column(name="custom_field_max_length")
	private String customFieldMaxLength;

	@Column(name="custom_field_placeholder")
	private String customFieldPlaceholder;

	@Column(name="custom_field_size")
	private String customFieldSize;

	@Column(name="custom_field_disabled")
	private String customFieldDisabled;

	@Column(name="custom_field_autofocus")
	private String customFieldAutofocus;

	@Column(name="custom_field_checked")
	private String customFieldChecked;

	@Column(name="custom_field_required")
	private String customFieldRequired;

	@Column(name="is_Active")
	private String isActive;

	@Column(name="modified_on")
	@JsonIgnore
	private String modifiedOn;

	@Column(name="modified_by")
	@JsonIgnore
	private String modifiedBy;

}
