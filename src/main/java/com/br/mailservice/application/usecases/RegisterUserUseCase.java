package com.br.mailservice.application.usecases;

import com.br.mailservice.application.api.dtos.RegisterDTO;
import com.br.mailservice.application.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserUseCase {
    private final UserService userService;

    public RegisterUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void handle(RegisterDTO registerDTO) {
        userService.registerUser(
                registerDTO.getUsername(),
                registerDTO.getPassword(),
                registerDTO.getName(),
                registerDTO.getDocument()
        );
    }
}