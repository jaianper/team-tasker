package com.jaianper.teamtasker.team.infrastructure.persistence.mapper;

import com.jaianper.teamtasker.team.domain.model.TeamMember;
import com.jaianper.teamtasker.team.infrastructure.persistence.entity.TeamMemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMemberEntityMapper {
    TeamMemberEntity toEntity(TeamMember teamMember);
    TeamMember toDomain(TeamMemberEntity teamMemberEntity);
} 