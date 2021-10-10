package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Long>
{

}
