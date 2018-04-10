package com.example.demo.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.CustomField;
import com.example.demo.model.LeadCustomFieldValue;

public interface LeadCustomFieldValueRespository extends JpaRepository<LeadCustomFieldValue, Long>{

	@Query("select leadCustomFieldName from LeadCustomFieldValue where createdOn>?1 and createdOn<?2")
	public Set<String> findCustomField(LocalDateTime startDate, LocalDateTime endDate);
	
}
