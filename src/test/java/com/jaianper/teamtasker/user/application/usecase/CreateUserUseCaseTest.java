package com.jaianper.teamtasker.user.application.usecase;

import com.jaianper.teamtasker.user.domain.exception.UserAlreadyExistsException;
import com.jaianper.teamtasker.user.domain.exception.UserValidationException;
import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.domain.model.UserRole;
import com.jaianper.teamtasker.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private User validUser;
    private User savedUser;

    @BeforeEach
    void setUp() {
        validUser = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password123")
                .role(UserRole.MEMBER)
                .build();

        savedUser = User.builder()
                .id(UUID.randomUUID())
                .username("testuser")
                .email("test@example.com")
                .password("encodedPassword")
                .role(UserRole.MEMBER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void execute_WithValidUser_ShouldCreateUser() {
        // Given
        when(userRepository.existsByUsername(validUser.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(validUser.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(validUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // When
        User result = createUserUseCase.execute(validUser);

        // Then
        assertNotNull(result);
        assertEquals(savedUser.getId(), result.getId());
        assertEquals(savedUser.getUsername(), result.getUsername());
        assertEquals(savedUser.getEmail(), result.getEmail());
        assertEquals(savedUser.getRole(), result.getRole());
        verify(passwordEncoder).encode(validUser.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void execute_WithExistingUsername_ShouldThrowException() {
        // Given
        when(userRepository.existsByUsername(validUser.getUsername())).thenReturn(true);

        // When & Then
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> createUserUseCase.execute(validUser));
        assertEquals("Ya existe un usuario con el nombre 'testuser'", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithExistingEmail_ShouldThrowException() {
        // Given
        when(userRepository.existsByUsername(validUser.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(validUser.getEmail())).thenReturn(true);

        // When & Then
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> createUserUseCase.execute(validUser));
        assertEquals("Ya existe un usuario con el email 'test@example.com'", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithNullUsername_ShouldThrowException() {
        // Given
        User invalidUser = validUser.toBuilder().username(null).build();

        // When & Then
        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> createUserUseCase.execute(invalidUser));
        assertEquals("El nombre de usuario es obligatorio", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithEmptyUsername_ShouldThrowException() {
        // Given
        User invalidUser = validUser.toBuilder().username("").build();

        // When & Then
        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> createUserUseCase.execute(invalidUser));
        assertEquals("El nombre de usuario es obligatorio", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithNullEmail_ShouldThrowException() {
        // Given
        User invalidUser = validUser.toBuilder().email(null).build();

        // When & Then
        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> createUserUseCase.execute(invalidUser));
        assertEquals("El email es obligatorio", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithInvalidEmail_ShouldThrowException() {
        // Given
        User invalidUser = validUser.toBuilder().email("invalid-email").build();

        // When & Then
        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> createUserUseCase.execute(invalidUser));
        assertEquals("El email debe tener un formato válido", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithShortPassword_ShouldThrowException() {
        // Given
        User invalidUser = validUser.toBuilder().password("123").build();

        // When & Then
        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> createUserUseCase.execute(invalidUser));
        assertEquals("La contraseña debe tener al menos 6 caracteres", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void execute_WithNullRole_ShouldThrowException() {
        // Given
        User invalidUser = validUser.toBuilder().role(null).build();

        // When & Then
        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> createUserUseCase.execute(invalidUser));
        assertEquals("El rol es obligatorio", exception.getMessage());
        verify(userRepository, never()).save(any());
    }
} 