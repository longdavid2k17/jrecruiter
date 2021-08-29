package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.services.RecruiterAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recruiter")
public class RecruiterAccountController
{
    private final Logger logger = LoggerFactory.getLogger(RecruiterAccountController.class);
    private final RecruiterAccountService recruiterAccountService;

    public RecruiterAccountController(RecruiterAccountService recruiterAccountService)
    {
        this.recruiterAccountService = recruiterAccountService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        ResponseEntity<?> response = recruiterAccountService.getById(id);
        return response;
    }
}
