package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.JobOffer;
import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.repositories.JobOfferRepository;
import com.dkantoch.jrecruiter.repositories.UserRepository;
import com.dkantoch.jrecruiter.security.JWTUtils;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobOfferService
{
    private final Logger logger = LoggerFactory.getLogger(JobOfferService.class);
    private final JobOfferRepository jobOfferRepository;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    public JobOfferService(JobOfferRepository jobOfferRepository,UserRepository userRepository,JWTUtils jwtUtils)
    {
        this.jobOfferRepository = jobOfferRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
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
        List<JobOffer> offersList = jobOfferRepository.findAll();
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

    public ResponseEntity<?> getById(Long id)
    {
        Optional<JobOffer> offerOptional = jobOfferRepository.findById(id);
        if(offerOptional.isPresent())
        {
            return ResponseEntity.ok().body(offerOptional.get());
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Nie znaleziono pozycji!"));
    }

    public ResponseEntity<?> save(JobOffer jobOffer,String token)
    {
        if(token!=null)
        {
            String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
            Optional<User> userOptional = userRepository.findByUsername(username);
            if(userOptional.isPresent())
            {
                User user = userOptional.get();
                if(user.getRecruiter())
                {
                    if(jobOffer!=null)
                    {
                        logger.warn("Trying to save {}",jobOffer);
                        jobOffer.setCreationDate(new Date());
                        jobOffer.setModificationDate(new Date());
                        if(jobOffer.getId()==null)
                        {
                            try
                            {
                                if(jobOffer.getCompany()==null)
                                {
                                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ToJsonString.toJsonString("Wystąpił błąd! Do oferty musi być przypisana firma"));
                                }
                                if(jobOffer.getContractType()==null)
                                {
                                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ToJsonString.toJsonString("Wystąpił błąd! W ofercie musi być informacja o typie umowy"));
                                }
                                JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
                                return ResponseEntity.ok().body(savedJobOffer);
                            }
                            catch (Exception e)
                            {
                                logger.error(e.getMessage(),e);
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ToJsonString.toJsonString("Wystąpił błąd! "+e.getMessage()));
                            }
                        }
                        else
                        {
                            try
                            {
                                JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
                                return ResponseEntity.ok().body(savedJobOffer);
                            }
                            catch (Exception e)
                            {
                                logger.error(e.getMessage(),e);
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ToJsonString.toJsonString("Wystąpił błąd! "+e.getMessage()));
                            }
                        }
                    }
                    else
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ToJsonString.toJsonString("Przesłana encja jest pusta"));
                }
                else
                    return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(ToJsonString.toJsonString("Do wykonania tej operacji musisz być zalogowany jako moderator"));
            }
            else
                return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(ToJsonString.toJsonString("Do wykonania tej operacji musisz być zalogowany"));

        }
        else
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(ToJsonString.toJsonString("Do wykonania tej operacji musisz być zalogowany. W nagłówkach brakuje informacji o użytkowniku"));

    }

    public ResponseEntity<?> getContractTypes()
    {
        List<JobOffer> offersList = jobOfferRepository.findAll();
        List<String> finalList = new ArrayList<>();
        for(JobOffer offer : offersList)
        {
            if(!finalList.contains(offer.getContractType()))
                finalList.add(offer.getContractType());
        }

        return ResponseEntity.ok().body(finalList);
    }

    public ResponseEntity<?> getAllCompanyOffers(Long id)
    {
        try
        {
            List<JobOffer> jobOffers = jobOfferRepository.findAllByCompany_IdEquals(id);
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

    public ResponseEntity<?> delete(Long id)
    {
        Optional<JobOffer> optionalJobOffer = jobOfferRepository.findById(id);
        if(optionalJobOffer.isPresent())
        {
            jobOfferRepository.delete(optionalJobOffer.get());
            return ResponseEntity.ok().body(ToJsonString.toJsonString("Usunięto ofertę '"+optionalJobOffer.get().getPositionTitle()+"'"));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ToJsonString.toJsonString("Nie znaleziono oferty o ID "+id));
    }
}
