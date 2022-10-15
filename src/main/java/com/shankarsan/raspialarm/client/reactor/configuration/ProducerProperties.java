package com.shankarsan.raspialarm.client.reactor.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "appconfig.kafka")
@SuppressWarnings("unused")
public class ProducerProperties {
	
	private String bootstrapServers;
	private Map<String, Object> producer;

}
