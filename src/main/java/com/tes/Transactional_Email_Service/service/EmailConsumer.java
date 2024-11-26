package com.tes.Transactional_Email_Service.service;

import com.tes.Transactional_Email_Service.dto.EmailRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tes.Transactional_Email_Service.config.RabbitMQConfig.EMAIL_QUEUE;


@Service
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = EMAIL_QUEUE)
    public  void processEmail(EmailRequest request){
        emailService.sendEmail(request);
        System.out.println("Received raw message: " + request);
    }
}
