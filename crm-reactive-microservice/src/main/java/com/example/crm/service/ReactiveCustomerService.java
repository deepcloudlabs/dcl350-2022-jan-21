package com.example.crm.service;

import org.springframework.stereotype.Service;

import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.response.CustomerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {

	public Mono<CustomerResponse> findById(String identity) {
		return null;
	}

	public Flux<CustomerResponse> findAllByPage(int page, int size) {
		return null;
	}

	public Mono<CustomerResponse> acquireCustomer(AcquireCustomerRequest request) {
		return null;
	}

	public Mono<CustomerResponse> releaseCustomer(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
