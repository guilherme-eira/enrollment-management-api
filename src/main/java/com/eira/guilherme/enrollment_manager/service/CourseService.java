package com.eira.guilherme.enrollment_manager.service;

import com.eira.guilherme.enrollment_manager.model.Course;
import com.eira.guilherme.enrollment_manager.model.Subject;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    List<Subject> getSubjectsByCourse(Long id);
}
