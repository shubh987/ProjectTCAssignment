package com.example.exception;

public class EmployeeAlreadyExist extends Exception{

	private static final long serialVersionUID = 1122009839905301088L;

	public EmployeeAlreadyExist(String msg) {
		super(msg);
	}
}
