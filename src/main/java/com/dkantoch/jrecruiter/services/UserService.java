package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.repositories.UserRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getUserByUserEmail(String email)
    {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent())
            return ResponseEntity.ok().body(optionalUser.get());
        else
        {
            logger.error("User with email {} not found!",email);
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Nie znaleziono powiązanego profilu!"));
        }
    }
}
