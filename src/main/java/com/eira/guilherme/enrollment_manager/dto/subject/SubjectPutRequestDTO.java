package com.eira.guilherme.enrollment_manager.dto.subject;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SubjectPutRequestDTO(
        @Size(max = 100, message = "o nome não pode ultrapassar 100 caracteres")
        String name,
        @Size(max = 100, message = "a descrição não pode ultrapassar 500 caracteres")
        String description,
        @Future
        LocalDate startDate,
        Long courseId,
        Long teacherId
) {}
