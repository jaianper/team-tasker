package com.jaianper.teamtasker.team.entrypoint.controller;

import com.jaianper.teamtasker.team.application.dto.*;
import com.jaianper.teamtasker.team.application.mapper.TeamMapper;
import com.jaianper.teamtasker.team.application.mapper.TeamMemberMapper;
import com.jaianper.teamtasker.team.application.usecase.*;
import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.domain.model.TeamMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Teams", description = "Operaciones para gestionar equipos")
@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final CreateTeamUseCase createTeamUseCase;
    private final GetAllTeamsUseCase getAllTeamsUseCase;
    private final GetTeamByIdUseCase getTeamByIdUseCase;
    private final UpdateTeamUseCase updateTeamUseCase;
    private final DeleteTeamUseCase deleteTeamUseCase;
    private final AddMemberToTeamUseCase addMemberToTeamUseCase;
    private final RemoveMemberFromTeamUseCase removeMemberFromTeamUseCase;
    private final GetTeamMembersUseCase getTeamMembersUseCase;

    private final TeamMapper teamMapper;
    private final TeamMemberMapper teamMemberMapper;

    @Operation(summary = "Crear un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody CreateTeamRequestDTO requestDTO) {
        Team created = createTeamUseCase.execute(teamMapper.toDomain(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMapper.toDto(created));
    }

    @Operation(summary = "Listar todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipos listados exitosamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<Team> teams = getAllTeamsUseCase.execute();
        List<TeamResponseDTO> dtos = teams.stream().map(teamMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Obtener equipo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable UUID id) {
        Optional<Team> team = getTeamByIdUseCase.execute(id);
        return team.map(value -> ResponseEntity.ok(teamMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable UUID id, @Valid @RequestBody UpdateTeamRequestDTO requestDTO) {
        Team teamToUpdate = teamMapper.toDomain(requestDTO);
        teamToUpdate.setId(id);
        Team updated = updateTeamUseCase.execute(teamToUpdate);
        return ResponseEntity.ok(teamMapper.toDto(updated));
    }

    @Operation(summary = "Eliminar un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Equipo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
        deleteTeamUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Agregar miembro al equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Miembro agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @PostMapping("/{teamId}/members")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamMemberResponseDTO> addMemberToTeam(
            @PathVariable UUID teamId,
            @Valid @RequestBody AddMemberRequestDTO requestDTO) {
        TeamMember member = addMemberToTeamUseCase.execute(teamId, requestDTO.getUserId(), requestDTO.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMemberMapper.toDto(member));
    }

    @Operation(summary = "Remover miembro del equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Miembro removido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo o miembro no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @DeleteMapping("/{teamId}/members/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeMemberFromTeam(@PathVariable UUID teamId, @PathVariable UUID userId) {
        removeMemberFromTeamUseCase.execute(teamId, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener miembros del equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Miembros obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @GetMapping("/{teamId}/members")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeamMemberResponseDTO>> getTeamMembers(@PathVariable UUID teamId) {
        List<TeamMember> members = getTeamMembersUseCase.execute(teamId);
        List<TeamMemberResponseDTO> dtos = members.stream().map(teamMemberMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
} 