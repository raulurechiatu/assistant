package com.eos.apps.assistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SocketController {
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    @Scheduled(fixedRate = 5000)
//    public void sendMessage() throws InterruptedException {
//        Thread.sleep(1000); // simulated delay
//        System.out.println("Scheduled");
//        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        template.convertAndSend("/topic/messages", "Hello at " + time);
////        template.convertAndSend("Hello at " + time);
//    }
//
//    @MessageMapping("/items")
//    public String items() throws InterruptedException {
//        Thread.sleep(1000); // simulated delay
//        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        return "Hello at " + time;
//    }
}
