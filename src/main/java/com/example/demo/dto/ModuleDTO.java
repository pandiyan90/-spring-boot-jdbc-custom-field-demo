package com.example.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ModuleDTO {
	
	@NotNull
	private String moduleName;
	
}
