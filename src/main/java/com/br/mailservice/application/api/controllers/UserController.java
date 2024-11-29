package com.br.mailservice.application.api.controllers;

import com.br.mailservice.application.api.dtos.LoginDTO;
import com.br.mailservice.application.api.dtos.RegisterDTO;
import com.br.mailservice.application.api.responses.ResponseDTO;
import com.br.mailservice.application.usecases.LoginUseCase;
import com.br.mailservice.application.usecases.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final LoginUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(LoginUseCase loginUseCase, RegisterUserUseCase registerUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        loginUseCase.handle(loginDTO)
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        registerUserUseCase.handle(registerDTO);
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Registrado com sucesso"
                )
        );
    }
}