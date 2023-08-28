package com.learn_spring_full_stack.full_stack_backend.service;

import java.io.IOException;
import java.util.Collection;

import com.learn_spring_full_stack.full_stack_backend.model.Server;

public interface ServerService {
	Server create(Server server);

	Server ping(String ipAddress) throws IOException;

	Collection<Server> list(int limit);

	Server get(Long id);

	Server update(Server server);

	Boolean delete(Long id);
}
