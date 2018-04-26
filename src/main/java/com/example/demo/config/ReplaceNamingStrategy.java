package com.example.demo.config;

import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class ReplaceNamingStrategy extends PropertyNamingStrategy{

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> replaceMap;

	public ReplaceNamingStrategy(Map<?, ?> replaceMap) {
		this.replaceMap = replaceMap;
	}
	
	@Override
	public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
		if(replaceMap.containsKey(defaultName))
			return (String) replaceMap.get(defaultName);

		return super.nameForGetterMethod(config, method, defaultName);
	}

}
