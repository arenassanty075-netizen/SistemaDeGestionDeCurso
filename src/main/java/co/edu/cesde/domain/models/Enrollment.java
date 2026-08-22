package co.edu.cesde.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "enrollment_id_pk")
    private String id;

    @ManyToOne
    @JoinColumn (name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name =  "course_id", nullable = false )
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnrollmentStatus status;

    @Column(name = "enrollment_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @Column(name = "enrollment_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.status = EnrollmentStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


}
