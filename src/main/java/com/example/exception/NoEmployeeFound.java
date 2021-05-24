package com.example.exception;


public class NoEmployeeFound extends Exception{
	
	private static final long serialVersionUID = 4783332065685262530L;

	public NoEmployeeFound(String msg) {
		super(msg);
	}

}
