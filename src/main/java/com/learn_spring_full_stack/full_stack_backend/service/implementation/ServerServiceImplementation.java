package com.learn_spring_full_stack.full_stack_backend.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn_spring_full_stack.full_stack_backend.enumeration.Status;
import com.learn_spring_full_stack.full_stack_backend.model.Server;
import com.learn_spring_full_stack.full_stack_backend.repository.ServerRepository;
import com.learn_spring_full_stack.full_stack_backend.service.ServerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

	private final ServerRepository serverRepository;

	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(this.setServerImageUrl());
		return this.serverRepository.save(server);
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");

		return this.serverRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by Id: {}", id);

		return this.serverRepository.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server.getName());

		return this.serverRepository.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by Id: {}", id);

		this.serverRepository.deleteById(id);

		return Boolean.TRUE;
	}

	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Pinging server: {}", ipAddress);

		var server = this.serverRepository.findByIpAddress(ipAddress);

		var address = InetAddress.getByName(ipAddress);

		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);

		this.serverRepository.save(server);

		return server;
	}

	private String setServerImageUrl() {
		String imageName = "server1.jpg";

		String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageName)
				.toUriString();

		log.info("Image url: {}", imageUrl);

		return imageUrl;
	}

}
