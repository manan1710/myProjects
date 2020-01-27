package com.calculator;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalculatorRepository extends MongoRepository<InputBean, Integer> {

}
