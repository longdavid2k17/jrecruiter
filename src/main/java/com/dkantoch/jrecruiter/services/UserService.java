package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.repositories.UserRepository;
import com.dkantoch.jrecruiter.security.JWTUtils;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService
{
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final FileStoreService fileStoreService;

    public UserService(UserRepository userRepository,JWTUtils jwtUtils, FileStoreService fileStoreService)
    {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.fileStoreService = fileStoreService;
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

    public ResponseEntity<?> getUserByUserUsername(String username)
    {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent())
            return ResponseEntity.ok().body(optionalUser.get());
        else
        {
            logger.error("User with username {} not found!",username);
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
                if(user.getBio()!=null && !dbUser.getBio().equals(user.getBio()))
                    dbUser.setBio(user.getBio());

                User savedUser = userRepository.save(dbUser);
                return ResponseEntity.ok().body(savedUser);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        else
            return ResponseEntity.badRequest().body("Nie znaleziono powiązanego profilu!");
    }

    public ResponseEntity<?> uploadImage(MultipartFile multipartFile, String token)
    {
        try
        {
            if(token!=null)
            {
                String username = jwtUtils.getUserNameFromJwtToken(token);
                User user = (User) getUserByUserUsername(username).getBody();
                if(user!=null && !multipartFile.isEmpty())
                {
                    if(user.getProfileImgUrl()!=null)
                    {
                        boolean operationResult = fileStoreService.deleteOldImage(user.getProfileImgUrl());
                        if(!operationResult)
                            logger.error("Error while deleting old files from storage!");
                    }
                    String imageUrl = fileStoreService.saveFile(multipartFile,username,true);
                    if(imageUrl!=null && !imageUrl.equals(""))
                    {
                        user.setProfileImgUrl(imageUrl);
                        userRepository.save(user);
                        return ResponseEntity.ok().body(ToJsonString.toJsonString("Zapisano obraz!"));
                    }
                    else
                        return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błąd podczas zapisywania!"));

                }
                else
                    return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błąd! Nie znaleziono takiego użytkownika!"));
            }
            else return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błąd! Nie znaleziono takiego użytkownika!"));

        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błąd! "+e.getMessage()));
        }
    }

    public Optional<User> getUserById(Long id)
    {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser;
    }
}
