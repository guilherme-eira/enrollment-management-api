package com.eira.guilherme.enrollment_manager.model;

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
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Course updateCourse(Course update){
        if (update.getName() != null){
            this.name = update.getName();
        } if (update.description != null){
            this.description = update.getDescription();
        }
        return this;
    }
}
