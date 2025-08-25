package com.eira.guilherme.enrollment_manager.model;

import com.eira.guilherme.enrollment_manager.enumeration.EnrollmentStatus;
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
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Person student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Enumerated(value = EnumType.STRING)
    private EnrollmentStatus status;

    public Enrollment updateEnrollment(Enrollment update) {
        if (update.getStudent() != null) {
            this.student = update.getStudent();
        } if (update.getSubject() != null) {
            this.subject = update.getSubject();
        }
        return this;
    }

    public void cancel(){
        this.status = EnrollmentStatus.TRANCADO;
    }

    public void approve(){
        this.status = EnrollmentStatus.APROVADO;
    }

    public void fail(){
        this.status = EnrollmentStatus.REPROVADO;
    }
}
