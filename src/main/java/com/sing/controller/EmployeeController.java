package com.sing.controller;

import com.sing.aspect.UserSafetyAgentPortalGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sing.model.Employee;
import com.sing.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@UserSafetyAgentPortalGuard(key = "accountId")
	@RequestMapping(value = "/add/employee/{accountId}/{name}/{empId}", method = RequestMethod.GET)
	public Employee addEmployee(@PathVariable(name = "accountId") String accountId, @PathVariable(name = "name") String name, @PathVariable(name = "empId") String empId) {
		System.out.println(accountId);
		return employeeService.createEmployee(name, empId);

	}

	@UserSafetyAgentPortalGuard(key = "accountId")
	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public Employee test(@PathVariable(name = "accountId") String accountId) {
		System.out.println(accountId);
		return null;

	}

	@RequestMapping(value = "/remove/employee", method = RequestMethod.GET)
	public String removeEmployee( @RequestParam("empId") String empId) {

		employeeService.deleteEmployee(empId);

		return "Employee removed";
	}

}