package com.br.mailservice.application.services;

import com.br.mailservice.domain.models.User;
import com.br.mailservice.domain.repository.UserRepository;
import com.br.mailservice.infrastruture.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return UserDetailsImpl.build(user);
    }
}