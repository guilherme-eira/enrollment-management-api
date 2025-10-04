package com.eira.guilherme.enrollment_manager.controller;

import com.eira.guilherme.enrollment_manager.dto.course.CoursePostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.course.CoursePutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.course.CourseResponseDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectResponseDTO;
import com.eira.guilherme.enrollment_manager.service.CourseService;
import com.eira.guilherme.enrollment_manager.mapper.CourseMapper;
import com.eira.guilherme.enrollment_manager.mapper.SubjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Tag(name = "Cursos", description = "Operações relacionadas a cursos")
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    CourseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo curso")
    public CourseResponseDTO createCourse(@Valid @RequestBody CoursePostRequestDTO dto) {
        return courseMapper.toDto(service.createCourse(courseMapper.toEntity(dto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todos os cursos cadastrados")
    public List<CourseResponseDTO> getAllCourses() {
        return service.getAllCourses().stream().map(d -> courseMapper.toDto(d)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um curso por id")
    public CourseResponseDTO getCourseById(@PathVariable Long id){
        return courseMapper.toDto(service.getCourseById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza as informações de um curso")
    public CourseResponseDTO updateCourse(@PathVariable Long id, @Valid @RequestBody CoursePutRequestDTO dto){
        return courseMapper.toDto(service.updateCourse(id, courseMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um curso")
    public void deleteCourse(@PathVariable Long id){
        service.deleteCourse(id);
    }

    @GetMapping("/{id}/subjects")
    @Operation(summary = "Lista todas as matérias associadas a um curso")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectResponseDTO> getSubjectsByCourse(@PathVariable Long id) {
        return service.getSubjectsByCourse(id).stream().map(subjectMapper::toDto).toList();
    }
}
