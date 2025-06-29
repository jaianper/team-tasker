package com.jaianper.teamtasker.team.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private UUID id;
    private String name;
    private String description;
    private UUID createdBy; // User ID who created the team
    private List<TeamMember> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Team updateFrom(Team source) {
        return this.toBuilder()
                .name(source.getName())
                .description(source.getDescription())
                .members(source.getMembers())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public boolean hasMember(UUID userId) {
        return members != null && members.stream()
                .anyMatch(member -> member.getUserId().equals(userId));
    }

    public boolean isMemberAdmin(UUID userId) {
        return members != null && members.stream()
                .anyMatch(member -> member.getUserId().equals(userId) && member.getRole() == TeamMemberRole.ADMIN);
    }

    public void addMember(TeamMember member) {
        if (members == null) {
            members = new java.util.ArrayList<>();
        }
        members.add(member);
    }

    public void removeMember(UUID userId) {
        if (members != null) {
            members.removeIf(member -> member.getUserId().equals(userId));
        }
    }
} 