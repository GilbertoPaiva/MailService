package com.br.mailservice.usecases.user;

import com.br.mailservice.dto.JwtResponseDTO;
import com.br.mailservice.dto.RegisterRequestDTO;
import com.br.mailservice.exception.UserAlreadyExistsException;
import com.br.mailservice.model.Auth;
import com.br.mailservice.repository.AuthRepository;
import com.br.mailservice.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class RegisterUserUseCase {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponseDTO handler(RegisterRequestDTO registerDTO) {

        if (authRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UserAlreadyExistsException("Nome de usuário já está em uso!");
        }


        Auth user = new Auth();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encoder.encode(registerDTO.getPassword()));

        authRepository.save(user);

        // Autenticar e gerar token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerDTO.getUsername(),
                        registerDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(registerDTO.getUsername());

        return new JwtResponseDTO(jwt);
    }
}