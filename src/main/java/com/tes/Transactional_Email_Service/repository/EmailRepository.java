package com.tes.Transactional_Email_Service.repository;


import com.tes.Transactional_Email_Service.entity.Email_e;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email_e, Long> {
}
