package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.GetEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee, GetEmployeeResponse>
	    EMPLOYEE_TO_GET_EMPLOYEE_RESPONSE_CONVERTER = 
	(context) -> {
	   var employee = context.getSource();
	   var fullname = employee.getFullname();
	   var response = new GetEmployeeResponse();
	   response.setIdentity(employee.getTcKimlikNo().getValue());
	   response.setFirstName(fullname.getFirst());
	   response.setLastName(fullname.getLast());
	   response.setIban(employee.getIban().getValue());
	   response.setSalary(employee.getSalary().getValue());
	   response.setPhoto(employee.getPhoto().getBase64Values());
	   response.setJobStyle(employee.getJobStyle());
	   response.setDepartments(employee.getDepartments());
	   response.setBirthYear(employee.getBirthYear().getValue());
	   return response;
	};
	private static final Converter<Employee, FireEmployeeResponse>
	EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER = 
	(context) -> {
		var employee = context.getSource();
		var fullname = employee.getFullname();
		var response = new FireEmployeeResponse();
		response.setIdentity(employee.getTcKimlikNo().getValue());
		response.setFullname(String.format("%s %s", 
				fullname.getFirst(),fullname.getLast()));
		response.setIban(employee.getIban().getValue());
		response.setSalary(employee.getSalary().getValue());
		response.setJobStyle(employee.getJobStyle().name());
		response.setBirthYear(employee.getBirthYear().getValue());
		return response;
	};
	private static final Converter<Employee, HireEmployeeResponse>
	EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER = 
	(context) -> {
		var employee = context.getSource();
		var fullname = employee.getFullname();
		var response = new HireEmployeeResponse();
		response.setIdentity(employee.getTcKimlikNo().getValue());
		response.setFullname(String.format("%s %s", 
				fullname.getFirst(),fullname.getLast()));
		return response;
	};
	private static final Converter<HireEmployeeRequest, Employee>
	HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = 
	(context) -> {
		var request = context.getSource();
		return new Employee.Builder()
				           .tcKimlikNo(request.getIdentity())
				           .fullname(request.getFirstName(), request.getLastName())
				           .iban(request.getIban())
				           .salary(request.getSalary())
				           .birthYear(request.getBirthYear())
				           .photo(request.getPhoto())
				           .jobStyle(request.getJobStyle())
				           .departments(request.getDepartments().toArray(new String[0]))
				           .build();
	};
	
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_GET_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, GetEmployeeResponse.class);
		mapper.addConverter(EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, FireEmployeeResponse.class);
		mapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, HireEmployeeResponse.class);
		mapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, 
				HireEmployeeRequest.class, Employee.class);
		return mapper;
	}
}
