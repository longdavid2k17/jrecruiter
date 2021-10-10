package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.services.RequirementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requirements")
@CrossOrigin("*")
public class RequirementController
{
    private final RequirementService requirementService;

    public RequirementController(RequirementService requirementService)
    {
        this.requirementService = requirementService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllRequirements()
    {
        return requirementService.getAll();
    }
}
