package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.TeamAlreadyExistsException;
import com.jaianper.teamtasker.team.domain.exception.TeamValidationException;
import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.domain.model.TeamMember;
import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import com.jaianper.teamtasker.team.domain.repository.TeamMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTeamUseCaseTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMemberRepository teamMemberRepository;

    @InjectMocks
    private CreateTeamUseCase createTeamUseCase;

    private Team validTeam;
    private Team savedTeam;
    private TeamMember creatorMember;

    @BeforeEach
    void setUp() {
        UUID creatorId = UUID.randomUUID();
        UUID teamId = UUID.randomUUID();
        
        validTeam = Team.builder()
                .name("Test Team")
                .description("Test Description")
                .createdBy(creatorId)
                .build();

        savedTeam = Team.builder()
                .id(teamId)
                .name("Test Team")
                .description("Test Description")
                .createdBy(creatorId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        creatorMember = TeamMember.builder()
                .id(UUID.randomUUID())
                .teamId(teamId)
                .userId(creatorId)
                .role(TeamMemberRole.ADMIN)
                .joinedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void execute_WithValidTeam_ShouldCreateTeam() {
        // Given
        when(teamRepository.existsByName(validTeam.getName())).thenReturn(false);
        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);
        when(teamMemberRepository.save(any(TeamMember.class))).thenReturn(creatorMember);

        // When
        Team result = createTeamUseCase.execute(validTeam);

        // Then
        assertNotNull(result);
        assertEquals(savedTeam.getId(), result.getId());
        assertEquals(savedTeam.getName(), result.getName());
        assertEquals(savedTeam.getDescription(), result.getDescription());
        assertEquals(savedTeam.getCreatedBy(), result.getCreatedBy());
        verify(teamRepository).save(any(Team.class));
        verify(teamMemberRepository).save(any(TeamMember.class));
    }

    @Test
    void execute_WithExistingTeamName_ShouldThrowException() {
        // Given
        when(teamRepository.existsByName(validTeam.getName())).thenReturn(true);

        // When & Then
        TeamAlreadyExistsException exception = assertThrows(TeamAlreadyExistsException.class,
                () -> createTeamUseCase.execute(validTeam));
        assertEquals("Ya existe un equipo con el nombre 'Test Team'", exception.getMessage());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void execute_WithNullName_ShouldThrowException() {
        // Given
        Team invalidTeam = validTeam.toBuilder().name(null).build();

        // When & Then
        TeamValidationException exception = assertThrows(TeamValidationException.class,
                () -> createTeamUseCase.execute(invalidTeam));
        assertEquals("El nombre del equipo es obligatorio", exception.getMessage());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void execute_WithEmptyName_ShouldThrowException() {
        // Given
        Team invalidTeam = validTeam.toBuilder().name("").build();

        // When & Then
        TeamValidationException exception = assertThrows(TeamValidationException.class,
                () -> createTeamUseCase.execute(invalidTeam));
        assertEquals("El nombre del equipo es obligatorio", exception.getMessage());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void execute_WithShortName_ShouldThrowException() {
        // Given
        Team invalidTeam = validTeam.toBuilder().name("ab").build();

        // When & Then
        TeamValidationException exception = assertThrows(TeamValidationException.class,
                () -> createTeamUseCase.execute(invalidTeam));
        assertEquals("El nombre del equipo debe tener al menos 3 caracteres", exception.getMessage());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void execute_WithLongName_ShouldThrowException() {
        // Given
        String longName = "a".repeat(101);
        Team invalidTeam = validTeam.toBuilder().name(longName).build();

        // When & Then
        TeamValidationException exception = assertThrows(TeamValidationException.class,
                () -> createTeamUseCase.execute(invalidTeam));
        assertEquals("El nombre del equipo no puede superar los 100 caracteres", exception.getMessage());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void execute_WithLongDescription_ShouldThrowException() {
        // Given
        String longDescription = "a".repeat(501);
        Team invalidTeam = validTeam.toBuilder().description(longDescription).build();

        // When & Then
        TeamValidationException exception = assertThrows(TeamValidationException.class,
                () -> createTeamUseCase.execute(invalidTeam));
        assertEquals("La descripción del equipo no puede superar los 500 caracteres", exception.getMessage());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void execute_WithNullCreatedBy_ShouldCreateTeamWithoutMember() {
        // Given
        Team teamWithoutCreator = validTeam.toBuilder().createdBy(null).build();
        when(teamRepository.existsByName(teamWithoutCreator.getName())).thenReturn(false);
        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);

        // When
        Team result = createTeamUseCase.execute(teamWithoutCreator);

        // Then
        assertNotNull(result);
        verify(teamRepository).save(any(Team.class));
        verify(teamMemberRepository, never()).save(any());
    }
} 