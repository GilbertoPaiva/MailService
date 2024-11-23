package com.br.mailservice.service;

import com.br.mailservice.dto.RegisterRequestDTO;
import com.br.mailservice.exception.UserAlreadyExistsException;
import com.br.mailservice.model.Auth;
import com.br.mailservice.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void registerUser(RegisterRequestDTO signUpRequest) throws UserAlreadyExistsException {

        if (authRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistsException("Nome de usuário já está em uso!");
        }

        Auth user = new Auth();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        authRepository.save(user);
    }
}
