package com.eira.guilherme.enrollment_manager.repository;

import com.eira.guilherme.enrollment_manager.enumeration.Role;
import com.eira.guilherme.enrollment_manager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
    List<Person> findByActiveTrue();
    Optional<Person> findByIdAndActiveTrue(Long id);
}
