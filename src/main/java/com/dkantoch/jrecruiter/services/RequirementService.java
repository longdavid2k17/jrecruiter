package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.Requirement;
import com.dkantoch.jrecruiter.repositories.RequirementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RequirementService
{
    private final RequirementRepository requirementRepository;

    public RequirementService(RequirementRepository requirementRepository)
    {
        this.requirementRepository = requirementRepository;
    }

    public ResponseEntity<?> getAll()
    {
        List<Requirement> requirementList = requirementRepository.findAll();
        requirementList.sort(Comparator.comparing(Requirement::getName,Comparator.naturalOrder()));
        return ResponseEntity.ok().body(requirementList);
    }
}
