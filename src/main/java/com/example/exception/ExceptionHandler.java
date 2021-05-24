package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoEmployeeFound.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String noEmployeeFound(NoEmployeeFound noEmployeeFound) {
		return  noEmployeeFound.getMessage();
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler(EmployeeAlreadyExist.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String employeeAlreadyExist(EmployeeAlreadyExist employeeAlreadyExist) {
		return  employeeAlreadyExist.getMessage();
	}

	
	@org.springframework.web.bind.annotation.ExceptionHandler(EmptyInputFieldException.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String emptyInputFieldException(EmptyInputFieldException emptyInputFieldException) {
		return  emptyInputFieldException.getMessage();
	}
	
	
}
