package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentProcessRepository extends JpaRepository<RecruitmentProcess,Long>
{
    List<RecruitmentProcess> findAllByUserId(Long id);
}
