package com.example.repository;

import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.Employee;
import com.example.exception.NoEmployeeFound;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String sql;

	RowMapper<Employee> rowMapper = (rs, rowNum) -> {
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getInt("employeeId"));
		employee.setName(rs.getString("name"));
		employee.setDepartment(rs.getString("department"));
		employee.setSalary(rs.getInt("salary"));
		employee.setAddress(rs.getString("address"));
		return employee;

	};

	@Override
	public boolean addEmployee(Employee employee) {
		sql = "insert into employee(employeeId,name,department,salary,address) values(?,?,?,?,?)";
		return (jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getName(), employee.getDepartment(),
				employee.getSalary(), employee.getAddress()) == 1) ;
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws EmptyResultDataAccessException, NoEmployeeFound {
		sql = "select * from employee where employeeId=?";
		Object[] args = { employeeId };
		Employee employee = new Employee();
		try {
			employee = jdbcTemplate.queryForObject(sql, rowMapper, args);
			return employee;
		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			emptyResultDataAccessException.getMessage();
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		sql = "select * from employee";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public boolean updateEmployee(int employeeId, Employee employee) {
		sql = "update employee set name=?,department=?,salary=?,address=? where employeeId=?";
		return (jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getSalary(),
				employee.getAddress(), employee.getEmployeeId()) == 1) ;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		sql = "delete from employee where employeeId=?";
		return (jdbcTemplate.update(sql, employeeId) == 1) ;
	}
}
