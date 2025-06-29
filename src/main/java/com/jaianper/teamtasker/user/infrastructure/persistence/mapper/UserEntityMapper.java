package com.jaianper.teamtasker.user.infrastructure.persistence.mapper;

import com.jaianper.teamtasker.user.domain.model.User;
import com.jaianper.teamtasker.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
} 