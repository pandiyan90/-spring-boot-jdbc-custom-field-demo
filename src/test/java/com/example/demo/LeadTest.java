package com.example.demo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Id;

import org.junit.Test;

import com.example.demo.dto.Custom;
import com.example.demo.model.Lead;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeadTest {

	@Test
	public void leadFields(){
		Field[] fields = new Lead().getClass().getDeclaredFields();
		List<String> columnNames = Stream.of(fields).filter(c -> c.getAnnotation(Custom.class) != null).map(c -> c.getName()).collect(Collectors.toList()); //forEach(a -> log.info("field: "+a.getAnnotation(Id.class)));
		columnNames.stream().forEach(a -> log.info("Field Name: "+a));
	}
	
}
