package com.piedpiper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.piedpiper.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
	Employee findByEmail(String email);

	Employee findUserByEmail(String email);

}
