package com.br.mailservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.mailservice.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, UUID> {

    Optional<Auth> findByUsername(String username);

    Boolean existsByUsername(String username);

}