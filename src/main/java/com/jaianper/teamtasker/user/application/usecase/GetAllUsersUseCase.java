package com.jaianper.teamtasker.user.application.usecase;

import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {
    private final UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }
} 