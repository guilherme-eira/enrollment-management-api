package com.eira.guilherme.enrollment_manager.repository;

import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Boolean existsByName(String name);
    List<Subject> findByTeacher(Person teacher);
    List<Subject> findByCourse(Course course);
    Boolean existsByTeacher(Person teacher);
    Boolean existsByCourse(Course course);
}
