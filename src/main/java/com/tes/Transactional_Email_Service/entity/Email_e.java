package com.tes.Transactional_Email_Service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "emails")
public class Email_e {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String status;

    private String error_message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_At = LocalDateTime.now();

    private LocalDateTime updated_At;
}
