package com.spring.practice.hibernateannotation.exception;

public class DatabaseException extends Exception {
	
	private static final long serialVersionUID = -7352761178524616644L;

	public DatabaseException(String message) {
		super(message);
	}
	
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseException(Throwable cause) {
		super(cause);
	}
}
