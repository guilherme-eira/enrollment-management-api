package com.eira.guilherme.enrollment_manager.mapper;

import com.eira.guilherme.enrollment_manager.dto.person.PersonPostRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.person.PersonPutRequestDTO;
import com.eira.guilherme.enrollment_manager.dto.person.PersonResponseDTO;
import com.eira.guilherme.enrollment_manager.model.Person;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PersonMapper {

    public Person toEntity(PersonPostRequestDTO dto) {
        return new Person(null, dto.name(), dto.email(), dto.document(), Objects.requireNonNullElse(dto.active(), true), dto.role());
    };

    public Person toEntity(PersonPutRequestDTO dto) {
        return new Person(null, dto.name(), dto.email(), null, null, dto.role());
    }

    public PersonResponseDTO toDto(Person entity) {
        return new PersonResponseDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getRole());
    }
}
