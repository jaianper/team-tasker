package com.jaianper.teamtasker.user.application.usecase;

import com.jaianper.teamtasker.user.domain.exception.UserNotFoundException;
import com.jaianper.teamtasker.user.domain.exception.UserValidationException;
import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user) {
        validateUser(user);
        
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con ID: " + user.getId()));

        // Check if username is being changed and if it already exists
        if (!existing.getUsername().equals(user.getUsername()) && 
            userRepository.existsByUsername(user.getUsername())) {
            throw new UserValidationException("Ya existe un usuario con el nombre '" + user.getUsername() + "'");
        }
        
        // Check if email is being changed and if it already exists
        if (!existing.getEmail().equals(user.getEmail()) && 
            userRepository.existsByEmail(user.getEmail())) {
            throw new UserValidationException("Ya existe un usuario con el email '" + user.getEmail() + "'");
        }

        // Update user with new data
        User updatedUser = existing.updateFrom(user);
        
        // If password is being updated, encode it
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // Keep existing password
            updatedUser.setPassword(existing.getPassword());
        }
        
        updatedUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(updatedUser);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new UserValidationException("El nombre de usuario es obligatorio");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new UserValidationException("El email es obligatorio");
        }
        
        if (user.getRole() == null) {
            throw new UserValidationException("El rol es obligatorio");
        }
        
        // Basic email validation
        if (!user.getEmail().contains("@")) {
            throw new UserValidationException("El email debe tener un formato válido");
        }
        
        // Password strength validation (only if password is being updated)
        if (user.getPassword() != null && !user.getPassword().isEmpty() && user.getPassword().length() < 6) {
            throw new UserValidationException("La contraseña debe tener al menos 6 caracteres");
        }
    }
} 