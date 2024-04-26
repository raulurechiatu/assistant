package com.eos.apps.assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EntityScan(basePackages = {"com.eos.apps.assistant.controllers", "com.eos.apps.assistant.services"})
public class AssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}

}
