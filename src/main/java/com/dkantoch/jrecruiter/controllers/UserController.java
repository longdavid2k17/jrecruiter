package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.models.User;
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

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String id)
    {
        ResponseEntity<?> response = userService.getUserByUserId(id);
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user)
    {
        logger.warn("Recived: ID={}, {}",id,user);
        ResponseEntity<?> response = userService.updateUser(id,user);
        return response;
    }
}
