package com.example.demo.config;

import java.io.IOException;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CustomException(String message) {
		super(message);
	}

}
