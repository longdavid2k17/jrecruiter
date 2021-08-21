package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.services.JobOfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/home")
    public ResponseEntity<?> getHomeOffers()
    {
        ResponseEntity<?> response = jobOfferService.getHomeOffers();
        return response;
    }
}
