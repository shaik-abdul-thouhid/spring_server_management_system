package com.learn_spring_full_stack.full_stack_backend.enumeration;

public enum Status {
	SERVER_UP("SERVER_UP"),
	SERVER_DOWN("SERVER_DOWN");

	private final String status;

	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}
