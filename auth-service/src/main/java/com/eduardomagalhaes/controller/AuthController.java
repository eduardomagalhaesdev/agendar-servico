package com.eduardomagalhaes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardomagalhaes.model.AuthRequest;
import com.eduardomagalhaes.model.AuthResponse;
import com.eduardomagalhaes.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Simula verificação de credenciais (ideal: consultar banco de dados)
        if ("admin@email.com".equals(request.getEmail()) && "123456".equals(request.getPassword())) {
            String token = jwtService.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).build();
    }
}