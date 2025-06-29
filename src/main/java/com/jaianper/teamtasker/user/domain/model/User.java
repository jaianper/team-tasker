package com.jaianper.teamtasker.user.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String username;
    private String email;
    private UserRole role;
    private String password; // ⚠️ Luego se aplicará hash y seguridad
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User updateFrom(User source) {
        return this.toBuilder()
                .username(source.getUsername())
                .email(source.getEmail())
                .role(source.getRole())
                .password(source.getPassword())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public boolean isAdmin() {
        return UserRole.ADMIN.equals(this.role);
    }

    public boolean isMember() {
        return UserRole.MEMBER.equals(this.role);
    }
}
