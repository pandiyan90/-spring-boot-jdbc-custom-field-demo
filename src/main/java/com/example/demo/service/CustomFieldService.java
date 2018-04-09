package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Module;
import com.example.demo.respository.CustomFieldRespository;
import com.example.demo.respository.CustomFieldValueRespository;
import com.example.demo.respository.ModuleRespository;

@Service
public class CustomFieldService {

	private ModuleRespository moduleRespository;
	
	private CustomFieldRespository customFieldRespository;

	private CustomFieldValueRespository customFieldValueRespository;
	
	public Module addModule(final Module module){
		return moduleRespository.save(module);
	}

}
