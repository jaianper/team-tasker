package com.jaianper.teamtasker.user.application.usecase;

import com.jaianper.teamtasker.user.domain.exception.UserAlreadyExistsException;
import com.jaianper.teamtasker.user.domain.exception.UserValidationException;
import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user) {
        validateUser(user);
        
        // Check if user already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Ya existe un usuario con el nombre '" + user.getUsername() + "'");
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Ya existe un usuario con el email '" + user.getEmail() + "'");
        }

        // Set default values
        User userToSave = user.toBuilder()
                .id(UUID.randomUUID())
                .password(passwordEncoder.encode(user.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return userRepository.save(userToSave);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new UserValidationException("El nombre de usuario es obligatorio");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new UserValidationException("El email es obligatorio");
        }
        
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new UserValidationException("La contraseña es obligatoria");
        }
        
        if (user.getRole() == null) {
            throw new UserValidationException("El rol es obligatorio");
        }
        
        // Basic email validation
        if (!user.getEmail().contains("@")) {
            throw new UserValidationException("El email debe tener un formato válido");
        }
        
        // Password strength validation
        if (user.getPassword().length() < 6) {
            throw new UserValidationException("La contraseña debe tener al menos 6 caracteres");
        }
    }
} 