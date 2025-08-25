package com.eira.guilherme.enrollment_manager.dto.enrollment;

import jakarta.validation.constraints.NotNull;

public record EnrollmentPostRequestDTO(
        @NotNull
        Long studentId,
        @NotNull
        Long subjectId
) {}
