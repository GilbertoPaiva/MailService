package com.br.mailservice.application.usecases;

import com.br.mailservice.application.api.dtos.LoginDTO;
import com.br.mailservice.application.api.responses.LoginResponseDTO;
import com.br.mailservice.infrastruture.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public LoginUseCase(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public LoginResponseDTO handle(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        String token = jwtUtils.generateToken(authentication.getName());

        return new LoginResponseDTO(token);
    }
}
