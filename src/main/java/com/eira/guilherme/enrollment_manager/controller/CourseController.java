package com.eira.guilherme.enrollment_manager.controller;

import com.eira.guilherme.enrollment_manager.dto.course.CoursePostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.course.CoursePutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.course.CourseResponseDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectResponseDTO;
import com.eira.guilherme.enrollment_manager.service.CourseService;
import com.eira.guilherme.enrollment_manager.mapper.CourseMapper;
import com.eira.guilherme.enrollment_manager.mapper.SubjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    CourseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponseDTO createCourse(@Valid @RequestBody CoursePostRequestDTO dto) {
        return courseMapper.toDto(service.createCourse(courseMapper.toEntity(dto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponseDTO> getAllCourses() {
        return service.getAllCourses().stream().map(d -> courseMapper.toDto(d)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponseDTO getCourseById(@PathVariable Long id){
        return courseMapper.toDto(service.getCourseById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponseDTO updateCourse(@PathVariable Long id, @Valid @RequestBody CoursePutRequestDTO dto){
        return courseMapper.toDto(service.updateCourse(id, courseMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id){
        service.deleteCourse(id);
    }

    @GetMapping("/{id}/subjects")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectResponseDTO> getSubjectsByCourse(@PathVariable Long id) {
        return service.getSubjectsByCourse(id).stream().map(subjectMapper::toDto).toList();
    }
}
