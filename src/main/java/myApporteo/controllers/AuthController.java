package myApporteo.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myApporteo.Dto.RoleDto;
import myApporteo.Dto.UserDto;
import myApporteo.entities.*;

import myApporteo.payload.request.LoginRequest;
import myApporteo.payload.request.SignupRequest;
import myApporteo.payload.response.JwtResponse;
import myApporteo.payload.response.MessageResponse;
import myApporteo.repositories.*;
import myApporteo.security.jwt.JwtUtils;
import myApporteo.security.services.UsersDetails;
import myApporteo.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsersRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder;
   
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	User user =  userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
    	 Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));

         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = jwtUtils.generateJwtToken(authentication);

         UsersDetails userDetails = (UsersDetails) authentication.getPrincipal();
         List<String> roles = userDetails.getAuthorities().stream()
                 .map(item -> item.getAuthority())
                 .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getAdresse(),signUpRequest.getTelephone(),signUpRequest.getNomAgence(),signUpRequest.getNomSociete(),signUpRequest.getSiret()
                ,signUpRequest.getNumCarteT(),signUpRequest.getCCI(),signUpRequest.getVille(),signUpRequest.getCodePostal(),signUpRequest.getAffairesPrtenaire(),signUpRequest.getType(),signUpRequest.getDateDeNaissance()
                ,signUpRequest.getAffairesApporteur());

        Set<String> strRoles = signUpRequest.getRole();
        System.out.println(user);
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        
                        roles.add(adminRole);

                        break;
                    case "apporteur":
                        Role apporteurRole = roleRepository.findByName(ERole.ROLE_APPORTEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(apporteurRole);

                        break;
                    case"partenaire":
                        Role partenaireRole = roleRepository.findByName(ERole.ROLE_PARTENAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(partenaireRole);
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
