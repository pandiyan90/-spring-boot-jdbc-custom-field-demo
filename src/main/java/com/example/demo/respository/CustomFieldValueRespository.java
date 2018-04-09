package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CustomField;
import com.example.demo.model.CustomFieldValue;

public interface CustomFieldValueRespository extends JpaRepository<CustomFieldValue, Long>{

}
