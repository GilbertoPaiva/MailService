package com.br.mailservice.usecases.user;

import com.br.mailservice.dto.JwtResponseDTO;
import com.br.mailservice.dto.LoginDTO;
import com.br.mailservice.exception.InvalidCredentialsException;
import com.br.mailservice.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class LoginUseCase {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponseDTO handler(LoginDTO loginDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(loginDTO.getUsername());

            return new JwtResponseDTO(jwt);
        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialsException("Usuário ou senha incorretos");
        }
    }
}