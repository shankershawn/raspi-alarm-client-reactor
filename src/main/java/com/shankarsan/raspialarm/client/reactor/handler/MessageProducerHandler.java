package com.shankarsan.raspialarm.client.reactor.handler;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.shankarsan.raspialarm.common.dto.KafkaPayload;
import com.shankarsan.raspialarm.common.dto.MessageDTO;
import com.shankarsan.raspialarm.common.dto.PayloadDTO;

import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
public class MessageProducerHandler implements HandlerFunction<ServerResponse>{
	
	@Autowired private KafkaSender<String, KafkaPayload<? extends PayloadDTO>> kafkaSender;
	

	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return kafkaSender.send(createSenderRecord(request))
				.map(e -> e.correlationMetadata())
				.transform(e -> ServerResponse.ok().body(e, String.class))
				.next();
	}
	
	private Mono<SenderRecord<String, KafkaPayload<? extends PayloadDTO>, String>> createSenderRecord(ServerRequest request){
		return request
				.bodyToMono(MessageDTO.class)
				.map(e -> SenderRecord.create(new ProducerRecord<>(request.pathVariable("topic"), new KafkaPayload<MessageDTO>(e)), "Posted New"));
	}

}
