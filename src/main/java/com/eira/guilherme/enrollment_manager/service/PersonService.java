package com.eira.guilherme.enrollment_manager.service;

import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person);
    List<Person> getAllPeople();
    Person getPersonById(Long id);
    Person updatePerson(Long id, Person person);
    void deletePerson(Long id);
    List<Subject> getSubjectsByTeacher(Long id);
    List<Enrollment> getEnrollmentsByStudent(Long id);
}
