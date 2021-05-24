package com.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.entity.Employee;
import com.example.exception.NoEmployeeFound;
import com.example.repository.IEmployeeRepository;
import com.example.service.IEmployeeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringJdbcApplicationTests {

	@Autowired
	private IEmployeeServices employeeServices;

	@MockBean
	private IEmployeeRepository employeeRepository;

	@Test
	public void getAllEmployee() throws NoEmployeeFound {
		when(employeeRepository.getAllEmployee()).thenReturn(
				Stream.of(new Employee(1, "name", "department", 10000, "address")).collect(Collectors.toList()));
		assertEquals(1, employeeServices.getAllEmployee().size());
	}

}
