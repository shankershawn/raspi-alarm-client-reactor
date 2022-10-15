package com.shankarsan.raspialarm.client.reactor.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shankarsan.raspialarm.common.dto.KafkaPayload;
import com.shankarsan.raspialarm.common.dto.MessageDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

@Service
public class MessageProducerService {
	
	@Autowired private KafkaSender<String, KafkaPayload<MessageDTO>> kafkaSender;
	
	public Flux<SenderResult<String>> sendMessage(String topic, MessageDTO messageDTO) {
		KafkaPayload<MessageDTO> payload = new KafkaPayload<>();
		payload.setPayload(messageDTO);
		return kafkaSender.send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic, "test", payload), "edfref")));
	}
}
