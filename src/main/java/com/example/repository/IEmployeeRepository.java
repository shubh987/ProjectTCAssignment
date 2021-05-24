package com.example.repository;


import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.example.entity.Employee;
import com.example.exception.NoEmployeeFound;


public interface IEmployeeRepository {

	public boolean addEmployee(Employee employee) ;

	public Employee getEmployeeById(int employeeId) throws EmptyResultDataAccessException, NoEmployeeFound;

	public List<Employee> getAllEmployee();

	boolean updateEmployee(int employeeId, Employee employee);

	public boolean deleteEmployee(int employeeId);

}
