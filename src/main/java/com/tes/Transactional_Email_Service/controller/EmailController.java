package com.tes.Transactional_Email_Service.controller;

import com.tes.Transactional_Email_Service.config.RabbitMQConfig;
import com.tes.Transactional_Email_Service.dto.EmailRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, request);
        return ResponseEntity.ok("Email request sent to the queue successfully.");
    }
}
