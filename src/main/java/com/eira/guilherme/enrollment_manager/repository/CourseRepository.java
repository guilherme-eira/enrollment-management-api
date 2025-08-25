package com.eira.guilherme.enrollment_manager.repository;

import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Boolean existsByName(String name);
}
