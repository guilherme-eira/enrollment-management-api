package com.eira.guilherme.enrollment_manager.controller;

import com.eira.guilherme.enrollment_manager.dto.enrollment.EnrollmentPostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.enrollment.EnrollmentPutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.enrollment.EnrollmentResponseDTO;
import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentAction;
import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;
import com.eira.guilherme.enrollment_manager.service.EnrollmentService;
import com.eira.guilherme.enrollment_manager.service.PersonService;
import com.eira.guilherme.enrollment_manager.service.SubjectService;
import com.eira.guilherme.enrollment_manager.mapper.EnrollmentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@Tag(name = "Matrículas", description = "Operações relacionadas a matrículas")
public class EnrollmentController {

    @Autowired
    EnrollmentMapper mapper;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    PersonService personService;

    @Autowired
    SubjectService subjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova matrícula")
    public EnrollmentResponseDTO createEnrollment(@Valid @RequestBody EnrollmentPostRequestDTO dto){
        var student = personService.getPersonById(dto.studentId());
        var subject = subjectService.getSubjectById(dto.subjectId());
        return mapper.toDto(enrollmentService.createEnrollment(mapper.toEntity(student, subject)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todas as matrículas existentes")
    public List<EnrollmentResponseDTO> getAllEnrollments(@RequestParam(required = false) EnrollmentStatus status){
        return enrollmentService.getAllEnrollments(status).stream().map(d -> mapper.toDto(d)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca uma matrícula por id")
    public EnrollmentResponseDTO getEnrollmentById(@PathVariable Long id){
        return mapper.toDto(enrollmentService.getEnrollmentById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza as informações de uma matrícula")
    public EnrollmentResponseDTO updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentPutRequestDTO dto) {
        Person student = null;
        Subject subject = null;
        if (dto.studentId() != null) student = personService.getPersonById(dto.studentId());
        if (dto.subjectId() != null) subject = subjectService.getSubjectById(dto.subjectId());
        return mapper.toDto(enrollmentService.updateEnrollment(id, mapper.toEntity(student, subject)));
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancela uma matrícula")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelEnrollment(@PathVariable Long id){
        enrollmentService.assignFinalStatus(id, EnrollmentAction.CANCEL);
    }

    @PatchMapping("/{id}/approve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aprova um estudante na disciplina vinculada à matrícula")
    public void approveStudent(@PathVariable Long id){
        enrollmentService.assignFinalStatus(id, EnrollmentAction.APPROVE);
    }

    @PatchMapping("/{id}/fail")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Reprova um estudante na disciplina vinculada à matrícula")
    public void failStudent(@PathVariable Long id){
        enrollmentService.assignFinalStatus(id, EnrollmentAction.FAIL);
    }

}
