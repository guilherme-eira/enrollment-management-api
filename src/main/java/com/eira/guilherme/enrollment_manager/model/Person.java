package com.eira.guilherme.enrollment_manager.model;

import com.eira.guilherme.enrollment_manager.enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Boolean active;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Person updatePerson(Person update) {
        if (update.getName() != null) {
            this.name = update.getName();
        }
        if (update.getEmail() != null) {
            this.email = update.getEmail();
        }
        if (update.role != null) {
            this.role = update.getRole();
        }
        return this;
    }

    public Person inactivate() {
        this.active = false;
        return this;
    }
}
