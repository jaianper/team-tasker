package com.jaianper.teamtasker.user.application.mapper;

import com.jaianper.teamtasker.user.application.dto.CreateUserRequestDTO;
import com.jaianper.teamtasker.user.application.dto.UpdateUserRequestDTO;
import com.jaianper.teamtasker.user.application.dto.UserResponseDTO;
import com.jaianper.teamtasker.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(CreateUserRequestDTO dto);
    User toDomain(UpdateUserRequestDTO dto);
    UserResponseDTO toDto(User user);
} 