package com.eira.guilherme.enrollment_manager.service;

import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.model.Subject;

import java.util.List;

public interface SubjectService {
    Subject createSubject(Subject subject);
    List<Subject> getAllSubjects();
    Subject getSubjectById(Long id);
    Subject updateSubject(Long id, Subject subject);
    void deleteSubject(Long id);
    List<Enrollment> getEnrollmentsBySubject(Long id);
}
