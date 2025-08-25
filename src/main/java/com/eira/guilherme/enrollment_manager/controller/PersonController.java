package com.eira.guilherme.enrollment_manager.controller;

import com.eira.guilherme.enrollment_manager.dto.enrollment.EnrollmentResponseDTO;
import com.eira.guilherme.enrollment_manager.dto.person.PersonPostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.person.PersonPutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.person.PersonResponseDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectResponseDTO;
import com.eira.guilherme.enrollment_manager.service.PersonService;
import com.eira.guilherme.enrollment_manager.mapper.EnrollmentMapper;
import com.eira.guilherme.enrollment_manager.mapper.PersonMapper;
import com.eira.guilherme.enrollment_manager.mapper.SubjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    EnrollmentMapper enrollmentMapper;

    @Autowired
    PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponseDTO createPerson(@Valid @RequestBody PersonPostRequestDTO dto){
       return personMapper.toDto(service.createPerson(personMapper.toEntity(dto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponseDTO> getAllPeople() {
        return service.getAllPeople().stream().map(personMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponseDTO getPersonById(@PathVariable Long id) {
        return personMapper.toDto(service.getPersonById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponseDTO updatePerson(@PathVariable Long id, @Valid @RequestBody PersonPutRequestDTO dto) {
        return personMapper.toDto(service.updatePerson(id, personMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
    }

    @GetMapping("/{teacherId}/subjects")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectResponseDTO> getSubjectsByTeacher(@PathVariable Long teacherId) {
        return service.getSubjectsByTeacher(teacherId).stream().map(subjectMapper::toDto).toList();
    }

    @GetMapping("/{studentId}/enrollments")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrollmentResponseDTO> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return service.getEnrollmentsByStudent(studentId).stream().map(enrollmentMapper::toDto).toList();
    }

}
