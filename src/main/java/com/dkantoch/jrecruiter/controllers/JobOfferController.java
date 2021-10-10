package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.models.JobOffer;
import com.dkantoch.jrecruiter.services.JobOfferService;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offers")
public class JobOfferController
{
    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService)
    {
        this.jobOfferService = jobOfferService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody JobOffer jobOffer, HttpServletRequest request)
    {
        final String authorizationHeaderValue = request.getHeader("Authorization");
        if(authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer"))
        {
            ResponseEntity<?> response = jobOfferService.save(jobOffer,authorizationHeaderValue);
            return response;
        }
        else
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(ToJsonString.toJsonString("Do wykonania tej operacji musisz być zalogowany!"));

    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getAllOfCompany(@PathVariable Long id)
    {
        ResponseEntity<?> response = jobOfferService.getAllCompanyOffers(id);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffers()
    {
        ResponseEntity<?> response = jobOfferService.getAllOffers();
        return response;
    }

    @GetMapping("/pageable")
    public ResponseEntity<?> getAllOffersPageable(Pageable pageable)
    {
        ResponseEntity<?> response = jobOfferService.getAllOffersPageable(pageable);
        return response;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        ResponseEntity<?> response = jobOfferService.getById(id);
        return response;
    }

    @GetMapping("/home")
    public ResponseEntity<?> getHomeOffers()
    {
        ResponseEntity<?> response = jobOfferService.getHomeOffers();
        return response;
    }

    @GetMapping("/contract-types")
    public ResponseEntity<?> getContractTypes()
    {
        ResponseEntity<?> response = jobOfferService.getContractTypes();
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        ResponseEntity<?> response = jobOfferService.delete(id);
        return response;
    }
}
