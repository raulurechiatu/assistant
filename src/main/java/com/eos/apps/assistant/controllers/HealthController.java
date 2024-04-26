package com.eos.apps.assistant.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Assistant services are up and running.");
    }
}
