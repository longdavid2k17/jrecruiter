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
    Page<JobOffer> findByPositionTitleContainsOrderByCreationDateDesc(String name, Pageable pageable);
    Page<JobOffer> findAllByContractTypeContains(String type, Pageable pageable);

    @Query("SELECT DISTINCT o.leadingTechnology FROM JobOffer o")
    List<String> findDistinctTechnology();

    @Query("SELECT o FROM JobOffer o WHERE o.company.id = ?1")
    Page<JobOffer> findAllByCompanyId(Long id, Pageable pageable);
}
