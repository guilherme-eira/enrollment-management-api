package com.eira.guilherme.enrollment_manager.dto.person;

import com.eira.guilherme.enrollment_manager.enumeration.Role;

public record PersonResponseDTO(
        Long id,
        String name,
        String email,
        Role role
) {
}
