package com.br.mailservice.controller;

import com.br.mailservice.dto.JwtResponseDTO;
import com.br.mailservice.dto.LoginDTO;
import com.br.mailservice.dto.RegisterRequestDTO;
import com.br.mailservice.dto.ResponseDTO;

import com.br.mailservice.usecases.user.LoginUseCase;
import com.br.mailservice.usecases.user.RegisterUserUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private LoginUseCase loginUseCase;

    @Autowired
    private RegisterUserUseCase registerUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        JwtResponseDTO response = loginUseCase.handler(loginDTO);
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        response
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerDTO) {
        JwtResponseDTO response = registerUserUseCase.handler(registerDTO);
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        response
                )
        );
    }
}