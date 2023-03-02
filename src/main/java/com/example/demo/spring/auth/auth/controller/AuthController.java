package com.example.demo.spring.auth.auth.controller;

import com.example.demo.spring.auth.auth.request.AuthenticationRequest;
import com.example.demo.spring.auth.auth.request.RegisterRequest;
import com.example.demo.spring.auth.auth.response.AuthenticationResponse;
import com.example.demo.spring.auth.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if(!authService.userDoesntExists(request.getEmail())) {
            throw new IllegalArgumentException("The user already exists!");
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest userReq) {
        return ResponseEntity.ok(authService.authenticate(userReq));
    }
}
