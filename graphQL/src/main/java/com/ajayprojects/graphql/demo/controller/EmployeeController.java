package com.ajayprojects.graphql.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ajayprojects.graphql.demo.model.Employee;
import com.ajayprojects.graphql.demo.model.Team;
import com.ajayprojects.graphql.demo.service.EmployeeServiceGraphQL;

import ch.qos.logback.core.model.conditional.ElseModel;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServiceGraphQL employeeServiceGraphQL;
	
	@QueryMapping
	public List<Employee> findAll(){
		return employeeServiceGraphQL.findAll();
	}
	
	@QueryMapping
	public Employee findOne(@Argument Integer id) {
		return employeeServiceGraphQL.findOne(id);
	}
	
	@MutationMapping
	public Employee createEmployee(@Argument String name, @Argument Team team) {
		return employeeServiceGraphQL.createEmployee(name, team);
	}
	
	
	@MutationMapping
	public Employee updateEmployee(@Argument Integer id,
									@Argument String name,
									@Argument Team team) {
		return employeeServiceGraphQL.updateEmployee(id, name, team);
	}
	
	@MutationMapping
	public Employee deleteEmployee(@Argument Integer id) {
		 return employeeServiceGraphQL.deleteEmployee(id);
	}
}
