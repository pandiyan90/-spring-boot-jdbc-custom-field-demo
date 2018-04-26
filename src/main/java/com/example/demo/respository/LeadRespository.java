package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Lead;

public interface LeadRespository extends JpaRepository<Lead, Long>{

	List<Lead> findByIsActive(boolean isActive);

}
