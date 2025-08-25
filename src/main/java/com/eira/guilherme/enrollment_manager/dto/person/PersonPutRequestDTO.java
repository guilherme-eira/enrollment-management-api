package com.eira.guilherme.enrollment_manager.dto.person;

import com.eira.guilherme.enrollment_manager.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record PersonPutRequestDTO(
        @Size(max = 100, message = "o nome não pode ultrapassar 100 caracteres")
        String name,
        @Email
        @Email(message = "utilize um email válido")
        String email,
        Role role
){}
