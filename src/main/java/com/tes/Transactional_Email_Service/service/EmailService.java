package com.tes.Transactional_Email_Service.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.tes.Transactional_Email_Service.dto.EmailRequest;
import com.tes.Transactional_Email_Service.entity.Email_e;
import com.tes.Transactional_Email_Service.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private SendGrid sendgrid;

    @Autowired
    private EmailRepository emailRepo;

    public void sendEmail(EmailRequest request) {
        Email_e emailEntity = new Email_e();

        emailEntity.setRecipient(request.getRecipient());
        emailEntity.setSubject(request.getSubject());
        emailEntity.setBody(request.getBody());
        emailEntity.setStatus("QUEUED");

        try {
            com.sendgrid.helpers.mail.objects.Email from = new com.sendgrid.helpers.mail.objects.Email("aaina0099@gmail.com");
            com.sendgrid.helpers.mail.objects.Email to = new com.sendgrid.helpers.mail.objects.Email(request.getRecipient());
            Content content = new Content("text/plain", request.getBody());
            Mail mail = new Mail(from, request.getSubject(), to, content);

            Request sgrequest = new Request();
            sgrequest.setMethod(Method.POST);
            sgrequest.setEndpoint("mail/send");
            sgrequest.setBody(mail.build());

            Response response = sendgrid.api(sgrequest);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                emailEntity.setStatus("SENT");
            } else {
                emailEntity.setStatus("FAILED");
                emailEntity.setError_message("Failed with Status code: " + response.getStatusCode());
            }
        } catch (IOException e) {
            emailEntity.setStatus("FAILED");
            emailEntity.setError_message(e.getMessage());
        }
        emailRepo.save(emailEntity);
    }
}
