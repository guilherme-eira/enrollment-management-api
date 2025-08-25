package com.eira.guilherme.enrollment_manager.dto.subject;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SubjectPostRequestDTO (
        @NotBlank
        @Size(max = 100, message = "o nome não pode ultrapassar 100 caracteres")
        String name,
        @NotBlank
        @Size(max = 100, message = "a descrição não pode ultrapassar 500 caracteres")
        String description,
        @Future
        LocalDate startDate,
        @NotNull
        Long courseId,
        @NotNull
        Long teacherId
){}
