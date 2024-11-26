package com.tes.Transactional_Email_Service;

import com.sendgrid.helpers.mail.objects.Email;

public class TestEmail {
    public static void main(String[] args) {
        Email email = new Email("test@example.com");
        System.out.println("Email object created: " + email);
    }
}
