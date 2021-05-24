package com.example.entity;

public class Employee {

	private int employeeId;

	private String name;

	private String department;

	private int salary;

	private String address;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String name, String department, int salary, String address) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.address = address;
	}

	public Employee(String name, String department, int salary, String address) {
		super();
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.address = address;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", department=" + department + ", salary="
				+ salary + ", address=" + address + "]";
	}

	
	
}
