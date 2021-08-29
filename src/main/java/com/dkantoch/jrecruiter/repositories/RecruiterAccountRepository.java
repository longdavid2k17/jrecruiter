package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.RecruiterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterAccountRepository extends JpaRepository<RecruiterAccount,Long>
{

}
