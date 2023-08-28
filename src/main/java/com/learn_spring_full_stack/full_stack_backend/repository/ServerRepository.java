package com.learn_spring_full_stack.full_stack_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn_spring_full_stack.full_stack_backend.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {

	Server findByIpAddress(String ipAddress);

}
