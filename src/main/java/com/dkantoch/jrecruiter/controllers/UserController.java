package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController
{
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<?> getUserByUserEmail(@PathVariable String email)
    {
        ResponseEntity<?> response = userService.getUserByUserEmail(email);
        return response;
    }
}
