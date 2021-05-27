package com.example.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Employee;
import com.example.exception.EmployeeAlreadyExist;
import com.example.exception.EmptyInputFieldException;
import com.example.exception.NoEmployeeFound;
import com.example.service.IEmployeeServices;

/**
 * 
 * @author shubhamdes
 *
 */
@RestController
@RequestMapping("employee")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	IEmployeeServices employeeServices;

	public static final Logger logger = LogManager.getLogger(EmployeeController.class.getName());

	// adding employee
	/**
	 * 
	 * @param employee
	 * @return
	 * @throws EmployeeAlreadyExist
	 * @throws EmptyInputFieldException
	 * @throws EmptyResultDataAccessException
	 * @throws NoEmployeeFound
	 */
	@PostMapping("/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee)
			throws EmployeeAlreadyExist, EmptyInputFieldException, EmptyResultDataAccessException, NoEmployeeFound {
		employeeServices.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.OK).body("Employee added Succesfully");
	}

	// getting all employess
	/**
	 * 
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getAllEmployee());
	}

	// getting employee by id as path variable
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getEmployeeById/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getEmployeeById(employeeId));
	}

	// updating employee
	/**
	 * 
	 * @param employeeId
	 * @param employee
	 * @return
	 * @throws NoEmployeeFound
	 */
	@PutMapping("/updateEmployee/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee)
			throws NoEmployeeFound {
		employeeServices.updateEmployee(employeeId, employee);
		return ResponseEntity.status(HttpStatus.OK).body("Employee Updated Sucessfully");
	}

	// deleting employee by id
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws NoEmployeeFound
	 */
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) throws NoEmployeeFound {
		employeeServices.deleteEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body("Employee deleted sucessfully");
	}

	// getting employees by salary range
	/**
	 * 
	 * @param minValue
	 * @param maxValue
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getEmployeeBySalaryRange")
	public ResponseEntity<List<Employee>> getEmployeeBySalaryRange(@RequestParam int minValue,
			@RequestParam int maxValue) throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getEmployeeBySalaryRange(minValue, maxValue));
	}

	// getting employees by department
	/**
	 * 
	 * @param departmentName
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getEmployeeByDepartment")
	public ResponseEntity<List<Employee>> getEmployeeByDepartment(@RequestParam String departmentName)
			throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getEmployeeByDepartment(departmentName));
	}

	// getting by specific salary range and department
	/**
	 * 
	 * @param department
	 * @param minValue
	 * @param maxValue
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getEmployeeWithDeptNSalRange")
	public ResponseEntity<List<Employee>> getEmployeeWithDeptNSalRange(@RequestParam String department,
			@RequestParam int minValue, @RequestParam int maxValue) throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK)
				.body(employeeServices.getEmployeeWithDeptNSalRange(department, minValue, maxValue));
	}

	// getting employees by salary range and department in HashMap
	/**
	 * 
	 * @param department
	 * @param minValue
	 * @param maxValue
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getEmployeeWithDeptNSalRangeToMap")
	public ResponseEntity<Map<Integer, Employee>> getEmployeeWithDeptNSalRangeToMap(@RequestParam String department,
			@RequestParam int minValue, @RequestParam int maxValue) throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK)
				.body(employeeServices.getEmployeeWithDeptNSalRangeToMap(department, minValue, maxValue));
	}

	/**
	 * 
	 * @param department
	 * @return
	 * @throws NoEmployeeFound
	 */
	@GetMapping("/getEmployeeToHashTable")
	public ResponseEntity<Hashtable<Integer, Employee>> getEmployeeToHashTable(@RequestParam String department)
			throws NoEmployeeFound {
		return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getEmployeeToHashTable(department));
	}

	// sorting employees by salary and name
	// salAsc, salDesc, nameAsc, nameDesc --------->param values
	/**
	 * 
	 * @param value
	 * @return
	 */
	@GetMapping("/sorting")
	public ResponseEntity<List<Employee>> sorting(@RequestParam String value) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeServices.sortingEmployees(value));
	}

}
