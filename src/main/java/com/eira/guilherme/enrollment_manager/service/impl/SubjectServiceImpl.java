package com.eira.guilherme.enrollment_manager.service.impl;

import com.eira.guilherme.enrollment_manager.enumeration.Role;
import com.eira.guilherme.enrollment_manager.exception.BusinessException;
import com.eira.guilherme.enrollment_manager.exception.ResourceNotFoundException;
import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.model.Subject;
import com.eira.guilherme.enrollment_manager.repository.EnrollmentRepository;
import com.eira.guilherme.enrollment_manager.repository.SubjectRepository;
import com.eira.guilherme.enrollment_manager.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public Subject createSubject(Subject subject) {
        if (!subject.getTeacher().getRole().equals(Role.PROFESSOR)) {
            throw new BusinessException("É necessário que a pessoa possua o cargo de professor");
        }
        if (subjectRepository.existsByName(subject.getName())) {
            throw new BusinessException("Esta matéria já existe");
        }
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há nenhuma matéria com este id"));
    }

    @Override
    public Subject updateSubject(Long id, Subject update) {
        var subjectToBeUpdated = getSubjectById(id);
        if (!update.getTeacher().getRole().equals(Role.PROFESSOR)) {
            throw new BusinessException("É necessário que a pessoa possua o cargo de professor");
        }
        if (!subjectToBeUpdated.getName().equals(update.getName()) && subjectRepository.existsByName(update.getName())) {
            throw new BusinessException("Esta matéria já existe");
        }
        return subjectRepository.save(subjectToBeUpdated.updateSubject(update));
    }

    @Override
    public void deleteSubject(Long id) {
        var subjectToBeDeleted = getSubjectById(id);
        if (enrollmentRepository.existsBySubject(subjectToBeDeleted)) {
            throw new BusinessException("Não é possível excluir esta matéria pois há matrículas associadas a ela");
        } else {
            subjectRepository.delete(subjectToBeDeleted);
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsBySubject(Long id) {
        var subject = getSubjectById(id);
        return enrollmentRepository.findBySubject(subject);
    }
}
