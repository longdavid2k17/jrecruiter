package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.RecruiterAccount;
import com.dkantoch.jrecruiter.repositories.RecruiterAccountRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterAccountService
{
    private final RecruiterAccountRepository recruiterAccountRepository;

    public RecruiterAccountService(RecruiterAccountRepository recruiterAccountRepository)
    {
        this.recruiterAccountRepository = recruiterAccountRepository;
    }

    public RecruiterAccount saveAccount(RecruiterAccount entity)
    {
        RecruiterAccount savedEntity = recruiterAccountRepository.save(entity);
        return savedEntity;
    }

    public ResponseEntity<?> getById(Long id)
    {
        if(id!=null)
        {
            Optional<RecruiterAccount> optionalRecruiterAccount = recruiterAccountRepository.findById(id);
            if(optionalRecruiterAccount.isPresent())
                return ResponseEntity.ok().body(optionalRecruiterAccount.get());
            else
                return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Nie znaleziono wskazanej firmy!"));
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Przekazany parametr jest niepoprawny!"));
    }
}
