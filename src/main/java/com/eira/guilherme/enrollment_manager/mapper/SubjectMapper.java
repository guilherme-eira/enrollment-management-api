package com.eira.guilherme.enrollment_manager.mapper;

import com.eira.guilherme.enrollment_manager.dto.subject.SubjectPostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectPutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.subject.SubjectResponseDTO;
import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public Subject toEntity(SubjectPostRequestDTO dto, Course course, Person person){
        return new Subject(null, dto.name(), dto.description(), dto.startDate(), course, person);
    }

    public Subject toEntity(SubjectPutRequestDTO dto,  Course course, Person person){
        return new Subject(null, dto.name(), dto.description(), dto.startDate(), course, person);
    }

    public SubjectResponseDTO toDto(Subject entity) {
        return new SubjectResponseDTO(entity.getId(), entity.getName(), entity.getDescription(),
                entity.getStartDate(), entity.getCourse().getId(), entity.getTeacher().getId());
    }
}
