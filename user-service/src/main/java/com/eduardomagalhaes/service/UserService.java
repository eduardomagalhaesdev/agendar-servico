package com.eduardomagalhaes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardomagalhaes.dto.UserDto;
import com.eduardomagalhaes.model.User;
import com.eduardomagalhaes.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Em produção, usar BCrypt!
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}

