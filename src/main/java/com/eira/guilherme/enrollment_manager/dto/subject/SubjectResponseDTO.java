package com.eira.guilherme.enrollment_manager.dto.subject;

import java.time.LocalDate;

public record SubjectResponseDTO (
        Long id,
        String name,
        String description,
        LocalDate startDate,
        Long courseId,
        Long teacherId
)
{}
