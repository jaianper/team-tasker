package com.jaianper.teamtasker.team.infrastructure.persistence.mapper;

import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.infrastructure.persistence.entity.TeamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamEntityMapper {
    TeamEntity toEntity(Team team);
    Team toDomain(TeamEntity teamEntity);
} 