package com.learn_spring_full_stack.full_stack_backend.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class CustomResponse {

	protected LocalDateTime timeStamp;

	protected int statusCode;

	protected HttpStatus status;

	protected String reason;

	protected String message;

	protected String developerMessage;

	protected Map<?, ?> data;

}
