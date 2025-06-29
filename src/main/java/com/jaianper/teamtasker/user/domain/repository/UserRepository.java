package com.jaianper.teamtasker.user.domain.repository;

import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.model.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findByRole(UserRole role);
    void deleteById(UUID id);
}
