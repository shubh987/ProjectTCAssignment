package com.example.service;


import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import com.example.entity.Employee;
import com.example.exception.EmployeeAlreadyExist;
import com.example.exception.EmptyInputFieldException;
import com.example.exception.NoEmployeeFound;

public interface IEmployeeServices {
	
	public boolean addEmployee(Employee employee) throws EmployeeAlreadyExist, EmptyInputFieldException, EmptyResultDataAccessException, NoEmployeeFound ;
	
	public  Employee getEmployeeById(int employeeId) throws NoEmployeeFound;
	
	public List<Employee> getAllEmployee() throws NoEmployeeFound;
	
	public boolean deleteEmployee(int employeeId) throws NoEmployeeFound;
	
	public boolean updateEmployee(int employeeId, Employee employee) throws NoEmployeeFound ;
	
	public List<Employee> getEmployeeBySalaryRange(int minvalue,int maxValuet) throws NoEmployeeFound;
	
	public List<Employee> getEmployeeByDepartment(String departmentName) throws NoEmployeeFound;
	
	public List<Employee> getEmployeeWithDeptNSalRange(String department,int minValue,int maxValue) throws NoEmployeeFound;
	
}
