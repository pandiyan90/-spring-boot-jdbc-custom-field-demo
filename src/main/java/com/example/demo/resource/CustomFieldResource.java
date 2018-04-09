package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CustomFieldService;

@RestController
@RequestMapping("/custom-field")
public class CustomFieldResource {

	@Autowired
	private CustomFieldService customFieldService;
	
	@GetMapping
	public void get(){
		
	}

	@PostMapping
	public void post(){
		
	}

	@PutMapping
	public void put(){
		
	}

	@DeleteMapping
	public void delete(){
		
	}

}
