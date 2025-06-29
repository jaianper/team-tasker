package com.jaianper.teamtasker.user.application.usecase;

import com.jaianper.teamtasker.user.domain.exception.UserNotFoundException;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public void execute(UUID id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException("No se encontró el usuario con ID: " + id);
        }
        userRepository.deleteById(id);
    }
} 