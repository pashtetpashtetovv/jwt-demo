package com.example.jwtdemo.controller;

import com.example.jwtdemo.config.security.jwt.JwtUtils;
import com.example.jwtdemo.model.ERole;
import com.example.jwtdemo.payload.request.SigninRequest;
import com.example.jwtdemo.payload.request.SignupRequest;
import com.example.jwtdemo.payload.response.JwtResponse;
import com.example.jwtdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/login/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SigninRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateGwtToken(authentication);

        return ResponseEntity.ok(
                new JwtResponse(token, authentication.getName()));
    }


    @PostMapping("signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signupRequest){
        Boolean isUserExists = userService.isUserExistsByUsername(signupRequest.getUsername());
        if(isUserExists){
            return ResponseEntity.badRequest().body("Username already exists");
        }

        userService.save(signupRequest.getUsername(),
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                Set.of(ERole.ROLE_USER));

        return ResponseEntity.ok("User created");
    }

}
