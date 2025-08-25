package com.eira.guilherme.enrollment_manager.mapper;

import com.eira.guilherme.enrollment_manager.dto.enrollment.EnrollmentResponseDTO;
import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;
import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(Person student, Subject subject) {
        return new Enrollment(null, student, subject, EnrollmentStatus.CURSANDO);
    }

    public EnrollmentResponseDTO toDto(Enrollment entity) {
        return new EnrollmentResponseDTO(entity.getId(), entity.getStudent().getId(), entity.getSubject().getId(), entity.getStatus());
    }
}
