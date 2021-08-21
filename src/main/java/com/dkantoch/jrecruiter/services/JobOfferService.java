package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.JobOffer;
import com.dkantoch.jrecruiter.repositories.JobOfferRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobOfferService
{
    private final Logger logger = LoggerFactory.getLogger(JobOfferService.class);
    private final JobOfferRepository jobOfferRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository)
    {
        this.jobOfferRepository = jobOfferRepository;
    }

    public ResponseEntity<?> getAllOffers()
    {
        try
        {
            List<JobOffer> jobOffers = jobOfferRepository.findAll();
            if(jobOffers.size()>0)
            {
                return ResponseEntity.ok().body(jobOffers);
            }
            else
                return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak pozycji!"));
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błąd! "+e.getMessage()));
        }
    }

    public ResponseEntity<?> getAllOffersPageable(Pageable pageable)
    {
        Page<JobOffer> jobOffers = jobOfferRepository.findAll(pageable);
        if(jobOffers.getContent().size()>0)
        {
            return ResponseEntity.ok().body(jobOffers);
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak pozycji!"));
    }

    public ResponseEntity<?> getHomeOffers()
    {
        List<JobOffer> offersList = (List<JobOffer>) getAllOffers().getBody();
        List<JobOffer> sorted = offersList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<JobOffer> homeList = new ArrayList<>();
        if(sorted.size()>0)
        {
            int counter = 1;
            for(JobOffer jobOffer : sorted)
            {
                homeList.add(jobOffer);
                if(counter<20)
                    counter++;
                else
                    break;
            }
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błąd podczas pobierania!"));
        return ResponseEntity.ok().body(homeList);
    }
}
