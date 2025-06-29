package com.jaianper.teamtasker.team.application.mapper;

import com.jaianper.teamtasker.team.application.dto.TeamMemberResponseDTO;
import com.jaianper.teamtasker.team.domain.model.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMemberMapper {
    TeamMemberMapper INSTANCE = Mappers.getMapper(TeamMemberMapper.class);

    TeamMemberResponseDTO toDto(TeamMember teamMember);
} 