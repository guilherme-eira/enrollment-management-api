package com.eira.guilherme.enrollment_manager.service.impl;

import com.eira.guilherme.enrollment_manager.enumeration.Role;
import com.eira.guilherme.enrollment_manager.exception.BusinessException;
import com.eira.guilherme.enrollment_manager.exception.ResourceNotFoundException;
import com.eira.guilherme.enrollment_manager.model.Enrollment;
import com.eira.guilherme.enrollment_manager.model.Person;
import com.eira.guilherme.enrollment_manager.model.Subject;
import com.eira.guilherme.enrollment_manager.repository.EnrollmentRepository;
import com.eira.guilherme.enrollment_manager.repository.PersonRepository;
import com.eira.guilherme.enrollment_manager.repository.SubjectRepository;
import com.eira.guilherme.enrollment_manager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public Person createPerson(Person person) {
        if (personRepository.existsByCpf(person.getCpf())) {
            throw new BusinessException("Este CPF já está cadastrado");
        }
        if (personRepository.existsByEmail(person.getEmail())) {
            throw new BusinessException("Este email já está cadastrado");
        }
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findByActiveTrue();
    }

    @Override
    public Person getPersonById(Long id) {
        var foundPerson = personRepository.findByIdAndActiveTrue(id);
        return foundPerson.orElseThrow(() -> new ResourceNotFoundException("Não há nenhuma pessoa ativa com este id"));
    }

    @Override
    public Person updatePerson(Long id, Person update) {
        var personToBeUpdated = getPersonById(id);
        if (!personToBeUpdated.getEmail().equals(update.getEmail()) && personRepository.existsByEmail(update.getEmail())) {
            throw new BusinessException("Este email já está cadastrado");
        }
        return personRepository.save(personToBeUpdated.updatePerson(update));
    }

    @Override
    public void deletePerson(Long id) {
        var personToBeDeleted = getPersonById(id);
        if (subjectRepository.existsByTeacher(personToBeDeleted)) {
            throw new BusinessException("Não é possível excluir este professor pois há matérias associadas a ele");
        } else if (enrollmentRepository.existsByStudent(personToBeDeleted)) {
            throw new BusinessException("Não é possível excluir este aluno pois há matrículas associadas a ele");
        } else {
            personRepository.save(personToBeDeleted.inactivate());
        }
    }

    @Override
    public List<Subject> getSubjectsByTeacher(Long id) {
        var teacher = getPersonById(id);
        if (!teacher.getRole().equals(Role.PROFESSOR)) {
            throw new BusinessException("É necessário que a pessoa possua o cargo de professor");
        }
        return subjectRepository.findByTeacher(teacher);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Long id) {
        var student = getPersonById(id);
        if (!student.getRole().equals(Role.ESTUDANTE)) {
            throw new BusinessException("É necessário que a pessoa possua o cargo de estudante");
        }
        return enrollmentRepository.findByStudent(student);
    }
}
