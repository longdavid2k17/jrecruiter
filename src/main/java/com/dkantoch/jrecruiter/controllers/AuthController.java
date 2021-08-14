package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.payload.request.LoginRequest;
import com.dkantoch.jrecruiter.payload.request.SignupRequest;
import com.dkantoch.jrecruiter.services.AuthorizationService;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    private final AuthorizationService authorizationService;

    public AuthController(AuthorizationService authorizationService)
    {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
    {
        if(loginRequest!=null)
        {
            ResponseEntity<?> authenticationState = authorizationService.authenticateUser(loginRequest);
            return authenticationState;
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Żądanie jest niepoprawne!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
    {
        if(signUpRequest!=null)
        {
            ResponseEntity<?> authenticationState = authorizationService.registerUser(signUpRequest);
            return authenticationState;
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Żądanie jest niepoprawne!"));
    }
}
