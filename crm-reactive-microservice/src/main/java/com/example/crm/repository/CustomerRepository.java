package com.example.crm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Mono;

public interface CustomerRepository extends 
                            ReactiveMongoRepository<CustomerDocument, String>{
	Mono<CustomerDocument> findByEmail(String email);
}
