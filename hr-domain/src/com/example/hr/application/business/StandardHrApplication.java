package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private EmployeeRepository employeeRepository;
	
	public StandardHrApplication(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var kimlik = employee.getTcKimlikNo();
		if (employeeRepository.existsByKimlikNo(kimlik))
			throw new ExistingEmployeeException(
					"Employee already exist", kimlik.getValue());
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo kimlik) {
		if (!employeeRepository.existsByKimlikNo(kimlik))
			throw new EmployeeNotFoundException(
					"Employee does not exist", kimlik.getValue());
		return employeeRepository.remove(kimlik);
	}

	@Override
	public Optional<Employee> findEmployeeByKimlikNo(TcKimlikNo kimlik) {
		return employeeRepository.findOneById(kimlik);
	}

}
