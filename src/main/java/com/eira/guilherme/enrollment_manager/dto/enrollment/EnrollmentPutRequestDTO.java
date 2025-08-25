package com.eira.guilherme.enrollment_manager.dto.enrollment;

public record EnrollmentPutRequestDTO(
        Long studentId,
        Long subjectId
) {}
