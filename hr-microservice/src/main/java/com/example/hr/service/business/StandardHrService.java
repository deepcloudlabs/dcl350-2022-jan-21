package com.example.hr.service.business;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.GetEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;

@Service
public class StandardHrService implements HrService {

	private HrApplication hrApplication;
	private ModelMapper modelMapper;
	
	public StandardHrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Override
	public GetEmployeeResponse findEmployeeByIdentity(String kimlikNo) {
		var employee = hrApplication.findEmployeeByKimlikNo(TcKimlikNo.valueOf(kimlikNo));
		return modelMapper.map(employee, GetEmployeeResponse.class);
	}

	@Override
    public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FireEmployeeResponse fireEmployeeByIdentity(String kimlikNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
