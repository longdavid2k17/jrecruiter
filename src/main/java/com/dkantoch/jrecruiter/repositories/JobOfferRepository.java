package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long>
{
    JobOffer getById(Long id);
    Page<JobOffer> findAll(Pageable pageable);
}
