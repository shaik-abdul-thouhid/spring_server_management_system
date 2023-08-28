package com.learn_spring_full_stack.full_stack_backend.resource;

import static java.util.Map.of;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn_spring_full_stack.full_stack_backend.enumeration.Status;
import com.learn_spring_full_stack.full_stack_backend.model.CustomResponse;
import com.learn_spring_full_stack.full_stack_backend.model.Server;
import com.learn_spring_full_stack.full_stack_backend.service.implementation.ServerServiceImplementation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/server")
@AllArgsConstructor
public class ServerResource {

	private final ServerServiceImplementation service;

	private static final String SERVER = "server";

	@GetMapping("/list")
	public ResponseEntity<CustomResponse> getServers() {
		return ResponseEntity.ok(
				CustomResponse.builder()
						.timeStamp(java.time.LocalDateTime.now())
						.data(of("servers", service.list(30)))
						.message("Servers Retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build());
	}

	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<CustomResponse> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
		var server = service.ping(ipAddress);

		return ResponseEntity.ok(
				CustomResponse.builder()
						.timeStamp(java.time.LocalDateTime.now())
						.data(of(server, server))
						.message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build());
	}

	@PostMapping(path = "/save", consumes = "application/json")
	public ResponseEntity<CustomResponse> saveServer(@RequestBody @Valid final Server server) {

		return ResponseEntity.ok(
				CustomResponse.builder()
						.timeStamp(java.time.LocalDateTime.now())
						.data(of(server, this.service.create(server)))
						.message("Server Created")
						.status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value())
						.build());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CustomResponse> saveServer(@PathVariable("id") Long id) {

		return ResponseEntity.ok(
				CustomResponse.builder()
						.timeStamp(java.time.LocalDateTime.now())
						.data(of(SERVER, this.service.get(id)))
						.message("Server retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomResponse> deleteServer(@PathVariable("id") Long id) {

		return ResponseEntity.ok(
				CustomResponse.builder()
						.timeStamp(java.time.LocalDateTime.now())
						.data(of("Deleted", this.service.delete(id)))
						.message("Server deleted")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build());
	}

	@GetMapping(path = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getServerImage(@PathVariable("imageName") String fileName) throws IOException {

		// return Files.readAllBytes(Paths.get(System.getProperty("user.home") +
		// "/Downloads/" + fileName));

		return Files.readAllBytes(Paths.get("C:\\Users\\Shaik Abdul Thouhid\\Downloads\\" + fileName));

	}

}
