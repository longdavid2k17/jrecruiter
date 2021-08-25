package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.payload.response.MessageResponse;
import com.dkantoch.jrecruiter.security.JWTUtils;
import com.dkantoch.jrecruiter.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController
{
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final JWTUtils jwtUtils;

    public UserController(UserService userService,JWTUtils jwtUtils)
    {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<?> getUserByUserEmail(@PathVariable String email)
    {
        ResponseEntity<?> response = userService.getUserByUserEmail(email);
        return response;
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String id)
    {
        ResponseEntity<?> response = userService.getUserByUserId(id);
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user)
    {
        ResponseEntity<?> response = userService.updateUser(id,user);
        return response;
    }

    /*@PostMapping("/upload_image")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token)
    {
        ResponseEntity<?> response = userService.uploadImage(file,token);
        return response;
    }*/
    @PostMapping("/upload_image")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception
    {
        final String authorizationHeaderValue = request.getHeader("Authorization");
        if(authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer"))
        {
            String token = authorizationHeaderValue.substring(7);
            ResponseEntity<?> response = userService.uploadImage(file,token);
            return response;
        }
        else
        {
            logger.info("Musisz być zalogowany do tej operacji!");
            return ResponseEntity.ok(new MessageResponse("Musisz być zalogowany do tej operacji!"));
        }
    }
}
