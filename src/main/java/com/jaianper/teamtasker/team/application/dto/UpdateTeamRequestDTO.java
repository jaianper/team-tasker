package com.jaianper.teamtasker.team.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTeamRequestDTO {
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del equipo debe tener entre 3 y 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción del equipo no puede superar los 500 caracteres")
    private String description;
} 