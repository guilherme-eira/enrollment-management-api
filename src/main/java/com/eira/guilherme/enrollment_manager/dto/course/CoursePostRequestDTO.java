package com.eira.guilherme.enrollment_manager.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CoursePostRequestDTO (
        @NotBlank
        @Size(max = 100, message = "o nome não pode ultrapassar 100 caracteres")
        String name,
        @NotBlank
        @Size(max = 100, message = "a descrição não pode ultrapassar 500 caracteres")
        String description
){}
