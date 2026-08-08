package co.edu.cesde.infrastructure;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.domain.models.Course;
import co.edu.cesde.domain.models.Enrollment;
import co.edu.cesde.domain.models.EnrollmentStatus;
import co.edu.cesde.domain.models.Student;

import java.time.LocalDate;

public class DataInitializer {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;


    public DataInitializer(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository) {

        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;

    }

    public void initialize() {
        initializeStudents();
        initializeCourses();
        initializeEnrollments();
    }

    private void initializeStudents() {

        Student student1 = new Student(
                1L,
                "Juan",
                "Pérez",
                "juan@correo.com",
                LocalDate.of(2000, 5, 15)
        );

        Student student2 = new Student(
                2L,
                "Ana",
                "Gómez",
                "ana@correo.com",
                LocalDate.of(2001, 8, 20)
        );

        Student student3 = new Student(
                3L,
                "Carlos",
                "Ruiz",
                "carlos@correo.com",
                LocalDate.of(1999, 3, 10)
        );

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
    }

    private void initializeCourses() {

        Course course1 = new Course(
                1L,
                "JAVA-101",
                "Java Fundamentals",
                "Fundamentos de programación en Java",
                30
        );

        Course course2 = new Course(
                2L,
                "SPR-201",
                "Spring Boot",
                "Desarrollo de aplicaciones con Spring Boot",
                25
        );

        Course course3 = new Course(
                3L,
                "DB-301",
                "Databases",
                "Fundamentos de bases de datos",
                30
        );

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
    }

    private void initializeEnrollments() {

        Enrollment enrollment1 = new Enrollment(
                1L,
                1L,
                1L,
                LocalDate.of(2026, 8, 1),
                EnrollmentStatus.ACTIVE
        );

        Enrollment enrollment2 = new Enrollment(
                2L,
                2L,
                2L,
                LocalDate.of(2026, 8, 1),
                EnrollmentStatus.ACTIVE
        );

        Enrollment enrollment3 = new Enrollment(
                3L,
                3L,
                3L,
                LocalDate.of(2026, 8, 1),
                EnrollmentStatus.ACTIVE
        );

        enrollmentRepository.save(enrollment1);
        enrollmentRepository.save(enrollment2);
        enrollmentRepository.save(enrollment3);
    }
}