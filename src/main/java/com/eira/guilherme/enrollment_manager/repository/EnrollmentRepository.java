package com.eira.guilherme.enrollment_manager.repository;

import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;
import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByStatus(EnrollmentStatus status);
    List<Enrollment> findByStudent(Person student);
    List<Enrollment> findBySubject(Subject subject);
    Boolean existsByStudent(Person student);
    Boolean existsBySubject(Subject subject);
}
