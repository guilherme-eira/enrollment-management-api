package com.eira.guilherme.enrollment_manager.controller;

import com.eira.guilherme.enrollment_manager.dto.enrollment.EnrollmentResponseDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectPostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectPutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectResponseDTO;
import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.service.CourseService;
import com.eira.guilherme.enrollment_manager.service.PersonService;
import com.eira.guilherme.enrollment_manager.service.SubjectService;
import com.eira.guilherme.enrollment_manager.mapper.EnrollmentMapper;
import com.eira.guilherme.enrollment_manager.mapper.SubjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@Tag(name = "Matérias", description = "Operações relacionadas a matérias")
public class SubjectController {

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    EnrollmentMapper enrollmentMapper;

    @Autowired
    SubjectService subjectService;

    @Autowired
    CourseService courseService;

    @Autowired
    PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra uma nova matéria")
    public SubjectResponseDTO createSubject(@Valid @RequestBody SubjectPostRequestDTO dto){
        var course = courseService.getCourseById(dto.courseId());
        var teacher = personService.getPersonById(dto.teacherId());
        return subjectMapper.toDto(subjectService.createSubject(subjectMapper.toEntity(dto, course, teacher)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todas as matérias cadastradas")
    public List<SubjectResponseDTO> getAllSubjects(){
        return subjectService.getAllSubjects().stream().map(d -> subjectMapper.toDto(d)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca uma matéria por id")
    public SubjectResponseDTO getSubjectById(@PathVariable Long id){
        return subjectMapper.toDto(subjectService.getSubjectById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza as informações de uma matéria")
    public SubjectResponseDTO updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectPutRequestDTO dto){
        Course course = null;
        Person teacher = null;
        if (dto.courseId() != null) course = courseService.getCourseById(dto.courseId());
        if (dto.teacherId() != null) teacher = personService.getPersonById(dto.teacherId());
        return subjectMapper.toDto(subjectService.updateSubject(id, subjectMapper.toEntity(dto, course, teacher)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui uma matéria")
    public void deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
    }

    @GetMapping("/{id}/enrollments")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todas as matrículas associadas a uma matéria")
    public List<EnrollmentResponseDTO> getEnrollmentsBySubject(@PathVariable Long id) {
        return subjectService.getEnrollmentsBySubject(id).stream().map(enrollmentMapper::toDto).toList();
    }
}
