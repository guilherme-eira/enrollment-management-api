package com.eira.guilherme.enrollment_manager.dto.person;

import com.eira.guilherme.enrollment_manager.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PersonPostRequestDTO(
        @NotBlank
        @Size(max = 100, message = "o nome não pode ultrapassar 100 caracteres")
        String name,
        @NotBlank
        @Size(max = 200, message = "o email não pode ultrapassar 200 caracteres")
        @Email(message = "utilize um email válido")
        String email,
        @NotBlank
        @Size(min = 11, max = 11, message = "utilize 11 dígitos para preencher o CPF - Ex: 12345678901")
        String document,
        Boolean active,
        @NotNull
        Role role
){}
