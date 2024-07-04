package com.ajayprojects.graphql.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.ajayprojects.graphql.demo.model.Employee;
import com.ajayprojects.graphql.demo.model.Team;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeServiceGraphQL {

	private List<Employee> employeeList=new ArrayList<>();
	AtomicInteger id= new AtomicInteger(0);
	
	@PostConstruct
	private void init() {
		employeeList.add(new Employee(id.incrementAndGet(), "Sam", Team.HR));
		employeeList.add(new Employee(id.incrementAndGet(), "Bob", Team.TECHNICAL));
		employeeList.add(new Employee(id.incrementAndGet(), "Tom", Team.MARKETING));
		employeeList.add(new Employee(id.incrementAndGet(), "Mox", Team.MANAGEMENT));
		employeeList.add(new Employee(id.incrementAndGet(), "Jack", Team.PRODUCT));
	}
	
	public List findAll() {
		return employeeList;
	}
	
	public Employee findOne(Integer id) {
		return employeeList.stream()
				.filter(emp-> emp.id() == id)
				.findFirst()
				.orElseThrow(()-> new IllegalArgumentException("no employee Found"));
	}
	
	public Employee createEmployee(String name, Team team) {
		Employee e=new Employee(id.incrementAndGet(), name, team);
		employeeList.add(e);
		return e;
		
	}
	
	public Employee deleteEmployee(Integer id) {
		//int index=employeeList.indexOf(id);
		Employee e= employeeList.stream()
				.filter(emp-> emp.id()==id)
				.findFirst()
				.orElseThrow(()-> new IllegalArgumentException());
		employeeList.remove(e);
		return e;
	}
	
	
	public Employee updateEmployee(Integer id, String name, Team team) {
		Employee upEm=new Employee(id, name, team);
		Optional<Employee> optEmp=employeeList.stream()
				.filter(emp-> emp.id()==id)
				.findFirst();
		
		if(optEmp.isPresent()) {
			Employee e1= optEmp.get();
			int index=employeeList.indexOf(e1);
			employeeList.set(index, upEm);
		}else {
			throw new IllegalArgumentException("Employee not found");
		}
		
		return upEm;
	}
}

