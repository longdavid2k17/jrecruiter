package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter,Long>
{
    Optional<Newsletter> findBySentDateAndIsSentEquals(Date sentDate,Boolean isSent);
}
