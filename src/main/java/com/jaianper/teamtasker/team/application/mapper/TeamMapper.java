package com.jaianper.teamtasker.team.application.mapper;

import com.jaianper.teamtasker.team.application.dto.CreateTeamRequestDTO;
import com.jaianper.teamtasker.team.application.dto.UpdateTeamRequestDTO;
import com.jaianper.teamtasker.team.application.dto.TeamResponseDTO;
import com.jaianper.teamtasker.team.domain.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team toDomain(CreateTeamRequestDTO dto);
    Team toDomain(UpdateTeamRequestDTO dto);
    TeamResponseDTO toDto(Team team);
} 