package com.tes.Transactional_Email_Service.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.io.Serializable;

@Data
public class EmailRequest implements Serializable {

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    @Email
    @NotBlank
    private String recipient;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
