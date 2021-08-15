package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.MailingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailingRepository extends JpaRepository<MailingEntity,Long>
{
    List<MailingEntity> findAllByIsActive(Boolean isActive);
    Optional<MailingEntity> findByEmailAndIsActive(String email, Boolean isActive);
    Optional<MailingEntity> findByEmail(String email);
}
