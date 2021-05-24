package com.example.exception;

public class EmptyInputFieldException extends Exception {

	private static final long serialVersionUID = -3709601103987909217L;

	public EmptyInputFieldException(String msg) {
		super(msg);
	}
}
