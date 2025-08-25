package com.eira.guilherme.enrollment_manager.dto.course;

import jakarta.validation.constraints.Size;

public record CoursePutRequestDTO (
        @Size(max = 100, message = "o nome não pode ultrapassar 100 caracteres")
        String name,
        @Size(max = 100, message = "a descrição não pode ultrapassar 500 caracteres")
        String description
)
{}
