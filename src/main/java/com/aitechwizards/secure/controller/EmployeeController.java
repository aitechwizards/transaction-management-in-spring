package com.aitechwizards.secure.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitechwizards.secure.domain.Employee;
import com.aitechwizards.secure.service.IEmployeeService;

/**
 * @author aitechwizards
 *
 */
@RestController
@RequestMapping("/info/")
public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/employInfo")
	// @CrossOrigin(origins = "http://localhost:8787")
	private List<Employee> getAllEmpl(Principal request) {
//		Principal principal = request.getUserPrincipal();
		LOG.info("Invoked {}", request.getName());

		return employeeService.getAllEmployees();
	}

}
