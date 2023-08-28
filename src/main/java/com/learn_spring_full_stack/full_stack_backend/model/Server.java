package com.learn_spring_full_stack.full_stack_backend.model;

import com.learn_spring_full_stack.full_stack_backend.enumeration.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String ipAddress;

	@NotEmpty(message = "IP Address cannot be empty or null")
	private String name;

	private String memory;

	private String type;

	private String imageUrl;

	private Status status;

}
