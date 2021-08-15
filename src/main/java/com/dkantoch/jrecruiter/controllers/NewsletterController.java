package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/newsletter")
public class NewsletterController
{
    private final Logger logger = LoggerFactory.getLogger(NewsletterController.class);
    private final MailService mailService;

    public NewsletterController(MailService mailService)
    {
        this.mailService = mailService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveNewEmailAddressForNewsletter(@Valid @RequestBody String emailAddress)
    {
        try
        {
            ResponseEntity<?> response = mailService.validateAndSave(emailAddress);
            return response;
        }
        catch (MessagingException e)
        {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<?> getAllMailingEntities()
    {
        ResponseEntity<?> response = mailService.getAllMailingEntities();
        return response;
    }

    @GetMapping("/all-pageable")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<?> getAllMailingEntitiesPageable(Pageable pageable)
    {
        ResponseEntity<?> response = mailService.getAllMailingEntitiesPageable(pageable);
        return response;
    }
}
