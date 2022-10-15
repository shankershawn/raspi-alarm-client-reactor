package com.shankarsan.raspialarm.client.reactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.raspialarm.client.reactor.service.MessageProducerService;
import com.shankarsan.raspialarm.common.dto.MessageDTO;

@RestController
public class MessageProducerController {
	
	@Autowired private MessageProducerService messageProducerService;

	@PostMapping(value = "/v2/message/{topic}")
	public ResponseEntity<String> postMessage(@PathVariable(name = "topic") String topic, @RequestBody MessageDTO messageDTO){
		messageProducerService.sendMessage(topic, messageDTO).subscribe();
		System.out.println("After call");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("Posted from new", HttpStatus.OK);
		return responseEntity;
	}
}
