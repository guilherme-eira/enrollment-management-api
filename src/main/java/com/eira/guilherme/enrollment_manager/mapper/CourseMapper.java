package com.eira.guilherme.enrollment_manager.mapper;

import com.eira.guilherme.enrollment_manager.dto.course.CoursePostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.course.CoursePutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.course.CourseResponseDTO;
import com.eira.guilherme.enrollment_manager.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CoursePostRequestDTO dto) {
        return new Course(null, dto.name(), dto.description());
    }

    public Course toEntity(CoursePutRequestDTO dto) {
        return new Course(null, dto.name(), dto.description());
    }

    public CourseResponseDTO toDto(Course entity) {
        return new CourseResponseDTO(entity.getId(), entity.getName(), entity.getDescription());
    }
}
