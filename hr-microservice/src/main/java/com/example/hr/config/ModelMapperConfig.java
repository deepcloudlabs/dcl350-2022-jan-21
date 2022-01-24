package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.response.GetEmployeeResponse;

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
	
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_GET_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, GetEmployeeResponse.class);
		return mapper;
	}
}
