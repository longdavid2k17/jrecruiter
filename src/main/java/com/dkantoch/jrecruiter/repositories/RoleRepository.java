package com.dkantoch.jrecruiter.repositories;

import com.dkantoch.jrecruiter.models.ERole;
import com.dkantoch.jrecruiter.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>
{
    Optional<Role> findByName(ERole name);
}
