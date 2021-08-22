package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.repositories.UserRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public ResponseEntity<?> getUserByUserId(String id)
    {
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(id));
        if(optionalUser.isPresent())
            return ResponseEntity.ok().body(optionalUser.get());
        else
        {
            logger.error("User with email {} not found!",id);
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Nie znaleziono powiązanego profilu!"));
        }
    }

    @Transactional
    public ResponseEntity<?> updateUser(String id, User user)
    {
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(id));
        if(optionalUser.isPresent())
        {
            try
            {
                User dbUser = optionalUser.get();
                if(user.getEmail()!=null && !dbUser.getEmail().equals(user.getEmail()))
                    dbUser.setEmail(user.getEmail());
                if(user.getName()!=null && !dbUser.getName().equals(user.getName()))
                    dbUser.setName(user.getName());
                if(user.getSurname()!=null && !dbUser.getSurname().equals(user.getSurname()))
                    dbUser.setSurname(user.getSurname());
                if(user.getPhoneNumber()!=null && !dbUser.getPhoneNumber().equals(user.getPhoneNumber()))
                    dbUser.setPhoneNumber(user.getPhoneNumber());
                if(user.getWebsiteUrl()!=null && !dbUser.getWebsiteUrl().equals(user.getWebsiteUrl()))
                    dbUser.setWebsiteUrl(user.getWebsiteUrl());
                if(user.getGithubUrl()!=null && !dbUser.getGithubUrl().equals(user.getGithubUrl()))
                    dbUser.setGithubUrl(user.getGithubUrl());
                if(user.getTwitterUrl()!=null && !dbUser.getTwitterUrl().equals(user.getTwitterUrl()))
                    dbUser.setTwitterUrl(user.getTwitterUrl());
                if(user.getProfileImgUrl()!=null && !dbUser.getProfileImgUrl().equals(user.getProfileImgUrl()))
                    dbUser.setProfileImgUrl(user.getProfileImgUrl());

                User savedUser = userRepository.save(dbUser);
                logger.info("Saved: {}",savedUser);
                return ResponseEntity.ok().body(savedUser);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        else
            return ResponseEntity.badRequest().body("Nie znaleziono powiązanego profilu!");
    }
}
