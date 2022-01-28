package com.example.crm.service;

import org.springframework.stereotype.Service;

import com.example.crm.dto.response.CustomerResponse;

import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {

	public Mono<CustomerResponse> findById(String identity) {
		return null;
	}

}
