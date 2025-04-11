package com.eduardomagalhaes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardomagalhaes.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
