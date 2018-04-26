package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CustomFieldPropertz;
import com.example.demo.model.Module;

public interface CustomFieldPropertzRespository extends JpaRepository<CustomFieldPropertz, Long>{

	List<CustomFieldPropertz> findAllByModule(Module module);

}
