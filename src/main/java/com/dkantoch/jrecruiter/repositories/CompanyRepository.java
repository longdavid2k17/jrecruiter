package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.Company;
import com.dkantoch.jrecruiter.models.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>
{
    Company getCompanyById(Long id);
}
