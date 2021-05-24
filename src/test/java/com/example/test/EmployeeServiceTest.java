package com.example.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entity.Employee;
import com.example.exception.EmployeeAlreadyExist;
import com.example.exception.EmptyInputFieldException;
import com.example.exception.NoEmployeeFound;
import com.example.repository.IEmployeeRepository;
import com.example.service.IEmployeeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceTest {

	@Autowired
	private IEmployeeServices employeeServices;

	@MockBean
	private IEmployeeRepository employeeRepository;

	@Test
	void testAddEmployee() throws EmptyResultDataAccessException, EmployeeAlreadyExist, EmptyInputFieldException, NoEmployeeFound {
	Employee employee=	new Employee(1, "name", "department", 10000, "address");
	when(employeeRepository.addEmployee(employee)).thenReturn(true);
	assertEquals(true, employeeServices.addEmployee(employee));
	}
	
	@Test
	void testUpdateEmployee() throws NoEmployeeFound {
		Employee employee=new Employee(1, "name", "department", 10000, "address");
		when(employeeRepository.updateEmployee(1, employee)).thenReturn(true);
		assertEquals(true, employeeRepository.updateEmployee(1, employee));
	}
	
	
	@Test
	void testDeleteEmployee() throws NoEmployeeFound {
		when(employeeRepository.deleteEmployee(1)).thenReturn(true);
		assertEquals(true, employeeRepository.deleteEmployee(1));
	}

	@Test
	void testGetEmployeeById() throws EmptyResultDataAccessException, NoEmployeeFound {
		when(employeeRepository.getEmployeeById(1))
				.thenReturn((new Employee(1, "name", "department", 10000, "address")));
		assertEquals(1, employeeServices.getEmployeeById(1).getEmployeeId());
	}

	@Test
	void testGetAllEmployee() throws NoEmployeeFound {
		when(employeeRepository.getAllEmployee()).thenReturn(
				Stream.of(new Employee(1, "name", "department", 10000, "address")).collect(Collectors.toList()));
		assertEquals(1, employeeServices.getAllEmployee().size());
	}

	

	@Test
	void testGetEmployeeBySalaryRange() throws EmptyResultDataAccessException, NoEmployeeFound {
		when(employeeRepository.getAllEmployee().stream().filter(i -> (i.getSalary() > 1000) && (i.getSalary() < 20000))
				.collect(Collectors.toList()))
				.thenReturn(Stream.of(new Employee(1, "name", "department", 10000, "address")).collect(Collectors.toList()));
		assertEquals(1, employeeServices.getEmployeeBySalaryRange(1000, 20000).size());
	}

	@Test
	void testGetEmployeeByDepartment() throws NoEmployeeFound {
		when(employeeRepository.getAllEmployee().stream().filter(i -> i.getDepartment().equals("department"))
				.collect(Collectors.toList()))
				.thenReturn(Stream.of(new Employee(1, "name", "department", 10000, "address")).collect(Collectors.toList()));
		assertEquals(1, employeeServices.getEmployeeByDepartment("department").size());
	}

	@Test
	void testGetEmployeeWithDeptNSalRange() throws NoEmployeeFound {
		when(employeeRepository.getAllEmployee().stream().filter(i -> i.getDepartment().equals("department")&&(i.getSalary() > 1000) && (i.getSalary() < 20000))
				.collect(Collectors.toList()))
				.thenReturn(Stream.of(new Employee(1, "name", "department", 10000, "address")).collect(Collectors.toList()));
		assertEquals(1, employeeServices.getEmployeeWithDeptNSalRange("department", 1000, 20000).size());
	}

}
