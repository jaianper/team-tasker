package com.jaianper.teamtasker.user.application.usecase;

import com.jaianper.teamtasker.user.domain.exception.UserNotFoundException;
import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserByUsernameUseCase {
    private final UserRepository userRepository;

    public Optional<User> execute(String username) {
        return userRepository.findByUsername(username);
    }

    public User executeOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con nombre: " + username));
    }
} 