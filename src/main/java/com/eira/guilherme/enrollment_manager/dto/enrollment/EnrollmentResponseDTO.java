package com.eira.guilherme.enrollment_manager.dto.enrollment;

import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;

public record EnrollmentResponseDTO(
        Long id,
        Long studentId,
        Long subjectId,
        EnrollmentStatus status
) {}
