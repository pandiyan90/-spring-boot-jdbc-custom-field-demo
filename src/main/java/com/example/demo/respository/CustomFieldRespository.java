package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CustomField;

public interface CustomFieldRespository extends JpaRepository<CustomField, Long>{

}
