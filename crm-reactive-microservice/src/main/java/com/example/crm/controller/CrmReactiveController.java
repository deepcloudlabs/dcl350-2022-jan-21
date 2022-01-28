package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.service.ReactiveCustomerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CrmReactiveController {
	@Autowired
	ReactiveCustomerService reactiveCustomerService;

	@GetMapping("{identity}")
	public Mono<CustomerResponse> findCustomerByIdentity(@PathVariable String identity) {
		return reactiveCustomerService.findById(identity);
	}
}
