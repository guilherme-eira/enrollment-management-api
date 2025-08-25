package com.eira.guilherme.enrollment_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Person teacher;

    public Subject updateSubject(Subject update){
        if (update.getName() != null){
            this.name = update.getName();
        } if (update.getDescription() != null) {
            this.description = update.getDescription();
        } if (update.getStartDate() != null){
            this.startDate = update.getStartDate();
        }if (update.getCourse() != null) {
            this.course = update.getCourse();
        } if (update.getTeacher() != null) {
            this.teacher = update.getTeacher();
        }
        return this;
    }
}
