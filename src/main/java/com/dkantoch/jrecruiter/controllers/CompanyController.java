package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.models.Company;
import com.dkantoch.jrecruiter.services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/company")
public class CompanyController
{
    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll()
    {
        ResponseEntity<?> response = companyService.getAllCompanies();
        return response;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        ResponseEntity<?> response = companyService.getById(id);
        return response;
    }

    @PostMapping("/save/forId/{id}")
    public ResponseEntity<?> createNewCompany(@RequestBody Company company, @PathVariable Long id)
    {
        logger.warn("Recived ID={}, data={}",id,company);
        ResponseEntity<?> response = companyService.createNewCompany(company,id);
        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCompany(@RequestBody Company company)
    {
        ResponseEntity<?> response = companyService.save(company);
        return response;
    }
}
