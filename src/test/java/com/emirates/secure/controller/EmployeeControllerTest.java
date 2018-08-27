package com.emirates.secure.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.emirates.secure.domain.Employee;
import com.emirates.secure.domain.EmployeeList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	TestRestTemplate restTemplate;
	URL base;
	@LocalServerPort
	int port;

	@Before
	public void setUp() throws Exception {
		restTemplate = new TestRestTemplate("Bharat", "singh");
		base = new URL("http://localhost:" + port);
	}

	@Test
	public void whenUserWithWrongCredentialsThanUnAuthoredPage() {
		restTemplate = new TestRestTemplate("Bharat", "singh");
		System.err.println(base.toString());
		ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(base.toString() + "/info/employInfo",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
				});
		// EmployeeList responseEntity = restTemplate.getForObject(base.toString() +
		// "/info/employInfo",
		// EmployeeList.class);
		System.err.println(responseEntity);

		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}

}
