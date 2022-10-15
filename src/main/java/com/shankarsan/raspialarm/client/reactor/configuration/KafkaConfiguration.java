package com.shankarsan.raspialarm.client.reactor.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shankarsan.raspialarm.common.dto.KafkaPayload;
import com.shankarsan.raspialarm.common.dto.MessageDTO;

import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaConfiguration {
	
	@Autowired private ProducerProperties producerProperties;
	
	@Bean("SenderOptions")
	public SenderOptions<String, KafkaPayload<MessageDTO>> getSenderOptions(){
		Map<String, Object> configProperties = new HashMap<>();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerProperties.getBootstrapServers());
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerProperties.getProducer().get("key-serializer"));
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerProperties.getProducer().get("value-serializer"));
		return SenderOptions.create(configProperties);
	}
	
	@Bean("KafkaSender")
	public KafkaSender<String, KafkaPayload<MessageDTO>> getKafkaSender(@Qualifier("SenderOptions") SenderOptions<String, KafkaPayload<MessageDTO>> senderOptions){
		return KafkaSender.create(senderOptions);
	}

}
