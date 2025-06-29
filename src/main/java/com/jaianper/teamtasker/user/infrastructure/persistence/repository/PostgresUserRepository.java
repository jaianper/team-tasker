package com.jaianper.teamtasker.user.infrastructure.persistence.repository;

import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.model.UserRole;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import com.jaianper.teamtasker.user.infrastructure.persistence.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostgresUserRepository implements UserRepository {

    private final UserJpaRepository userRepository;
    private final UserEntityMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.toDomain(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDomain).toList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findByRole(UserRole role) {
        return userRepository.findByRole(role).stream().map(userMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
} 