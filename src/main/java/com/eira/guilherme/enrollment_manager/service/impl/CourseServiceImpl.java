package com.eira.guilherme.enrollment_manager.service.impl;

import com.eira.guilherme.enrollment_manager.exception.BusinessException;
import com.eira.guilherme.enrollment_manager.exception.ResourceNotFoundException;
import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Subject;
import com.eira.guilherme.enrollment_manager.repository.CourseRepository;
import com.eira.guilherme.enrollment_manager.repository.SubjectRepository;
import com.eira.guilherme.enrollment_manager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Course createCourse(Course course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new BusinessException("Este curso já existe");
        }
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há nenhum curso com este id"));
    }

    @Override
    public Course updateCourse(Long id, Course update) {
        var courseToBeUpdated = getCourseById(id);
        if (!courseToBeUpdated.getName().equals(update.getName()) && courseRepository.existsByName(update.getName())) {
            throw new BusinessException("Este nome já está sendo utilizado");
        }
        return courseRepository.save(courseToBeUpdated.updateCourse(update));
    }

    @Override
    public void deleteCourse(Long id) {
        var courseToBeDeleted = getCourseById(id);
        if (subjectRepository.existsByCourse(courseToBeDeleted)) {
            throw new BusinessException("Não é possível excluir este curso pois há matérias associadas a ele");
        } else {
            courseRepository.delete(courseToBeDeleted);
        }
    }

    @Override
    public List<Subject> getSubjectsByCourse(Long id) {
        var course = getCourseById(id);
        return subjectRepository.findByCourse(course);
    }
}
