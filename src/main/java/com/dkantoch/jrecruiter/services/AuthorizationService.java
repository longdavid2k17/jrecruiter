package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.ERole;
import com.dkantoch.jrecruiter.models.Role;
import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.payload.request.LoginRequest;
import com.dkantoch.jrecruiter.payload.request.SignupRequest;
import com.dkantoch.jrecruiter.payload.response.JwtResponse;
import com.dkantoch.jrecruiter.payload.response.MessageResponse;
import com.dkantoch.jrecruiter.repositories.RoleRepository;
import com.dkantoch.jrecruiter.repositories.UserRepository;
import com.dkantoch.jrecruiter.security.JWTUtils;
import com.dkantoch.jrecruiter.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorizationService
{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JWTUtils jwtUtils;

    public AuthorizationService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JWTUtils jwtUtils)
    {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> registerUser(SignupRequest signupRequest)
    {
        if (userRepository.existsByUsername(signupRequest.getUsername()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd: Użytkownik z takim loginem istnieje!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd: Użytkownik z takim adresem email istnieje!"));
        }

        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()), signupRequest.getName(), signupRequest.getSurname(), signupRequest.getPhoneNumber());

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null)
        {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        else
        {
            strRoles.forEach(role ->
            {
                switch (role)
                {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
