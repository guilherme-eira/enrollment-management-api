package com.eira.guilherme.enrollment_manager.service;

import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentAction;
import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;
import com.eira.guilherme.enrollment_manager.model.Enrollment;

import java.util.List;

public interface EnrollmentService{
    Enrollment createEnrollment(Enrollment enrollment);
    List<Enrollment> getAllEnrollments(EnrollmentStatus status);
    Enrollment getEnrollmentById(Long id);
    Enrollment updateEnrollment(Long id, Enrollment update);
    void assignFinalStatus(Long id, EnrollmentAction action);
}
