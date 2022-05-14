package com.piedpiper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.piedpiper.model.Role;


public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}
