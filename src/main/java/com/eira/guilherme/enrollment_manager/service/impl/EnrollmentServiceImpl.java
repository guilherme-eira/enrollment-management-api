package com.eira.guilherme.enrollment_manager.service.impl;

import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentAction;
import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;
import com.eira.guilherme.enrollment_manager.enumeration.Role;
import com.eira.guilherme.enrollment_manager.exception.BusinessException;
import com.eira.guilherme.enrollment_manager.exception.ResourceNotFoundException;
import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.repository.EnrollmentRepository;
import com.eira.guilherme.enrollment_manager.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        validateRelatedEntities(enrollment);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments(EnrollmentStatus status) {
        if (status == null) {
            return enrollmentRepository.findAll();
        }
        return enrollmentRepository.findAllByStatus(status);
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há nenhuma matrícula com este id."));
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment update) {
        var enrollmentToBeUpdated = getEnrollmentById(id);
        if (enrollmentToBeUpdated.getStatus() != EnrollmentStatus.CURSANDO){
            throw new BusinessException("Só é permitido atualizar matrículas em disciplinas que estão em andamento");
        }
        validateRelatedEntities(update);
        return enrollmentRepository.save(enrollmentToBeUpdated.updateEnrollment(update));
    }

    @Override
    public void assignFinalStatus(Long id, EnrollmentAction action) {
        var enrollmentToBeUpdated = getEnrollmentById(id);
        if (enrollmentToBeUpdated.getStatus() != EnrollmentStatus.CURSANDO){
            throw new BusinessException("Só é permitido atualizar matrículas em disciplinas que estão em andamento");
        }
        switch (action) {
            case CANCEL -> enrollmentToBeUpdated.cancel();
            case APPROVE -> enrollmentToBeUpdated.approve();
            case FAIL -> enrollmentToBeUpdated.fail();
        }
        enrollmentRepository.save(enrollmentToBeUpdated);
    }

    private void validateRelatedEntities(Enrollment enrollment) {
        if (enrollment.getStudent() != null && !enrollment.getStudent().getRole().equals(Role.ESTUDANTE)) {
            throw new BusinessException("É necessário que a pessoa possua o cargo de estudante");
        }
        if (enrollment.getSubject() != null && enrollment.getSubject().getStartDate().isBefore(LocalDate.now())) {
            throw new BusinessException("A matéria escolhida já está em andamento");
        }
    }
}
