package com.br.mailservice.controller;

import com.br.mailservice.dto.LoginDTO;
import com.br.mailservice.dto.RegisterRequestDTO;
import com.br.mailservice.dto.JwtResponseDTO;
import com.br.mailservice.dto.MessageResponseDTO;

import com.br.mailservice.exception.UserAlreadyExistsException;
import com.br.mailservice.service.AuthenticationService;
import com.br.mailservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequest) {

        try {
            userService.registerUser(registerRequest);
            // Autenticar e gerar token
            JwtResponseDTO jwtResponse = authenticationService.authenticate(
                    registerRequest.getUsername(),
                    registerRequest.getPassword()
            );

            return ResponseEntity.ok(jwtResponse);

        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Erro: Nome de usuário já está em uso!"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        JwtResponseDTO jwtResponse = authenticationService.authenticate(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(jwtResponse);
    }
}