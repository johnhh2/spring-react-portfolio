package com.portfolio.controllers;

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

import com.portfolio.models.Account;
import com.portfolio.models.Hostname;
import com.portfolio.models.JwtResponse;
import com.portfolio.models.LoginRequest;
import com.portfolio.models.MessageResponse;
import com.portfolio.models.PortfolioCategory;
import com.portfolio.models.Role;
import com.portfolio.models.RoleEnum;
import com.portfolio.models.SignupRequest;
import com.portfolio.models.User;
import com.portfolio.repository.AccountRepository;
import com.portfolio.repository.HostnameRepository;
import com.portfolio.repository.PortfolioCategoryRepository;
import com.portfolio.repository.RoleRepository;
import com.portfolio.repository.UserRepository;
import com.portfolio.security.jwt.JwtUtils;
import com.portfolio.security.services.UserDetailsImpl;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PortfolioCategoryRepository portfolioCategoryRepository;

    @Autowired
    HostnameRepository hostnameRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
        String username = signUpRequest.getUsername();
        String password = encoder.encode(signUpRequest.getPassword());
        String email = signUpRequest.getEmail();
        String realname = signUpRequest.getRealname();
        int age = signUpRequest.getAge();
        boolean darkMode = signUpRequest.getDarkMode();
        Set<String> strRoles = signUpRequest.getRole();

        User user = new User(username, password, email, age);

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "mod":
                    Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        Account account = new Account(user, realname, darkMode);
        accountRepository.save(account);

        PortfolioCategory category = new PortfolioCategory(
            "Mobile Applications", "phone_iphone");
        account.addCategory(category);
        portfolioCategoryRepository.save(category);

        String host;
        if (username.equals("mpurnell1") || username.equals("johnhh2"))
            host = "localhost";
        else
            host = username;

        Hostname hostname = new Hostname(host, user, account);
        hostnameRepository.save(hostname);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
