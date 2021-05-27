package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.entity.Employee;
import com.example.exception.EmployeeAlreadyExist;
import com.example.exception.EmptyInputFieldException;
import com.example.exception.NoEmployeeFound;
import com.example.repository.IEmployeeRepository;

/**
 * 
 * @author shubhamdes
 *
 */
@Service
public class EmployeeServicesImpl implements IEmployeeServices {

	@Autowired
	private IEmployeeRepository employeeRepository;

	public static final Logger logger = LogManager.getLogger(EmployeeServicesImpl.class.getName());

	// adding employee
	@Override
	public boolean addEmployee(Employee employee)
			throws EmployeeAlreadyExist, EmptyInputFieldException, EmptyResultDataAccessException, NoEmployeeFound {
		if (employee.getName().isEmpty()) {
			logger.error("Name is empty");
			throw new EmptyInputFieldException("Name is empty...try again");
		} else if (employee.getDepartment().isEmpty()) {
			logger.error("Department Field is empty");
			throw new EmptyInputFieldException("Department Field is empty...try again");
		} else if (employee.getAddress().isEmpty()) {
			logger.error("Address Field is empty");
			throw new EmptyInputFieldException("Address Field is empty...try again");
		} else if (employee.getSalary() == 0) {
			logger.error("Salary is empty");
			throw new EmptyInputFieldException("Salary is empty...try again");
		} else if (employee.getEmployeeId() == 0) {
			logger.error("Employee Id is empty");
			throw new EmptyInputFieldException("Employee Id can't be empty...try again");
		} else {
			if (employeeRepository.getEmployeeById(employee.getEmployeeId()) != null) {
				logger.error("Employee with provided Id already exists");
				throw new EmployeeAlreadyExist("Employee with provided Id already exists");
			} else
				logger.info("Employee added sucessfully");
			return (employeeRepository.addEmployee(employee));
		}
	}

	// getting employeee by Id
	@Override
	public Employee getEmployeeById(int employeeId) throws NoEmployeeFound {
		Employee employee = employeeRepository.getEmployeeById(employeeId);
		if (employee != null) {
			logger.info("Employee found sucessfully");
			return employee;
		} else {
			logger.error("No employee found with input Employee Id");
			throw new NoEmployeeFound("No employee found with input Employee Id");
		}
	}

	// getting all employees
	@Override
	public List<Employee> getAllEmployee() throws NoEmployeeFound {
		List<Employee> employeeList = employeeRepository.getAllEmployee();
		if (employeeList.isEmpty()) {
			logger.error("No employee found");
			throw new NoEmployeeFound("No employee found");
		} else {
			logger.info("Employee list found ");
			return employeeList;
		}
	}

	// deleting employee by Id
	@Override
	public boolean deleteEmployee(int employeeId) throws NoEmployeeFound {
		if (employeeRepository.getEmployeeById(employeeId) != null) {
			if (employeeRepository.deleteEmployee(employeeId)) {
				logger.info("Employee deleted sucessfully ");
				return true;
			}
			logger.debug("Employee cannot be deleted ");
			return false;
		} else {
			logger.error("No employee found to delete");
			throw new NoEmployeeFound("Employee with given Employee Id not found");
		}
	}

	// update employee
	@Override
	public boolean updateEmployee(int employeeId, Employee employee) throws NoEmployeeFound {
		if (employeeRepository.getEmployeeById(employeeId) != null) {
			if (employeeRepository.updateEmployee(employeeId, employee)) {
				logger.info("Employee updated sucessfully ");
				return true;
			}
			logger.debug("Employee could not update ");
			return false;
		} else {
			logger.error("No employee found to update");
			throw new NoEmployeeFound("Employee with given Employee Id not found");
		}
	}

	// employee by salary range
	@Override
	public List<Employee> getEmployeeBySalaryRange(int minValue, int maxValue) throws NoEmployeeFound {
		List<Employee> employeeList;
		employeeList = getAllEmployee().stream().filter(i -> (i.getSalary() > minValue) && (i.getSalary() < maxValue))
				.collect(Collectors.toList());
		if (employeeList.isEmpty()) {
			logger.info("Could not find employees with given salary range");
			throw new NoEmployeeFound("Could not find employees with given salary range");
		} else {
			logger.info("List with expected salary range sent");
			return employeeList;
		}
	}

	// employees by department
	@Override
	public List<Employee> getEmployeeByDepartment(String departmentName) throws NoEmployeeFound {
		List<Employee> employeeList;
		employeeList = getAllEmployee().stream().filter(i -> i.getDepartment().equals(departmentName))
				.collect(Collectors.toList());
		if (employeeList.isEmpty()) {
			logger.error("Could not find employees with given department");
			throw new NoEmployeeFound("Could not find employees with given department");
		} else {
			logger.info("List with expected department sent");
			return employeeList;
		}
	}

	// employees by salary range and department
	@Override
	public List<Employee> getEmployeeWithDeptNSalRange(String department, int minValue, int maxValue)
			throws NoEmployeeFound {
		List<Employee> empList = new ArrayList<>();
		Employee employee;
		Iterator<Employee> iterator = getAllEmployee().iterator();
		while (iterator.hasNext()) {
			employee = iterator.next();
			if (employee.getDepartment().equals(department) && employee.getSalary() > minValue
					&& employee.getSalary() < maxValue) {
				empList.add(employee);
			}
		}
		if (empList.isEmpty()) {
			throw new NoEmployeeFound("No employee found for given requirements");
		} else
			return empList;
	}

	// employees by salary range and department in hashmap
	@Override
	public Map<Integer, Employee> getEmployeeWithDeptNSalRangeToMap(String department, int minValue, int maxValue)
			throws NoEmployeeFound {
		Integer i = 1;
		HashMap<Integer, Employee> empMap = new HashMap<>();
		Employee employee;
		ListIterator<Employee> listIterator = getAllEmployee().listIterator();
		while (listIterator.hasNext()) {
			employee = listIterator.next();
			if (employee.getDepartment().equals(department) && employee.getSalary() > minValue
					&& employee.getSalary() < maxValue) {
				empMap.put(i, employee);
				i++;
			}
		}
		if (empMap.isEmpty()) {
			throw new NoEmployeeFound("No employee found for given requirements");
		} else
			return empMap;
	}

	// employees in hashtable
	@Override
	public Hashtable<Integer, Employee> getEmployeeToHashTable(String department) throws NoEmployeeFound {
		Integer k = 1;
		Hashtable<Integer, Employee> employeeTable = new Hashtable<>();
		List<Employee> employeeList = employeeRepository.getAllEmployee().stream()
				.filter(i -> i.getDepartment().equals(department)).collect(Collectors.toList());
		for (Employee emp : employeeList) {
			employeeTable.put(k++, emp);
		}
		if (employeeTable.isEmpty()) {
			throw new NoEmployeeFound("Map is empty");
		} else {
			return employeeTable;
		}
	}

	// sorting by salary and name
	@Override
	public List<Employee> sortingEmployees(String value) {
		List<Employee> empList = employeeRepository.getAllEmployee();
		Comparator<Employee> comparator;

		switch (value) {
		case "salAsc":
			comparator = (emp1, emp2) -> emp1.getSalary() < emp2.getSalary() ? -1
					: emp1.getSalary() > emp2.getSalary() ? 1 : 0;
			Collections.sort(empList, comparator);
			break;
		case "salDesc":
			comparator = (emp1, emp2) -> emp1.getSalary() < emp2.getSalary() ? 1
					: emp1.getSalary() > emp2.getSalary() ? -1 : 0;
			Collections.sort(empList, comparator);
			break;
		case "nameAsc":
			comparator = (emp1, emp2) -> emp1.getName().compareTo(emp2.getName()) < 0 ? -1
					: emp1.getName().compareTo(emp2.getName()) > 0 ? 1 : 0;
			Collections.sort(empList, comparator);
			break;
		case "nameDesc":
			comparator = (emp1, emp2) -> emp1.getName().compareTo(emp2.getName()) < 0 ? 1
					: emp1.getName().compareTo(emp2.getName()) > 0 ? -1 : 0;
			Collections.sort(empList, comparator);
			break;
		default:
			break;
		}
		return empList;
	}

}
