package com.shankarsan.raspialarm.client.reactor.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.shankarsan.raspialarm.client.reactor.handler.MessageProducerHandler;

@Configuration
public class MessageProducerRouter {
	
	@Autowired private MessageProducerHandler messageProducerHandler; 
	
	@Bean
	public RouterFunction<ServerResponse> getMessageProducerRouter(){
		return RouterFunctions.route(RequestPredicates.POST("/v2/message/{topic}"), messageProducerHandler);
	}

}
