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

                "Juan",
                "Pérez",
                "juan@correo.com",
                LocalDate.of(2000, 5, 15)
        );

        Student student2 = new Student(

                "Ana",
                "Gómez",
                "ana@correo.com",
                LocalDate.of(2001, 8, 20)
        );

        Student student3 = new Student(

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

                "JAVA-101",
                "Java Fundamentals",
                "Fundamentos de programación en Java",
                30
        );

        Course course2 = new Course(

                "SPR-201",
                "Spring Boot",
                "Desarrollo de aplicaciones con Spring Boot",
                25
        );

        Course course3 = new Course(

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

        Student student1 = studentRepository
                .findById(1L)
                .orElseThrow();
        Student student2 =studentRepository
                .findById(2L)
                .orElseThrow();
        Student student3 = studentRepository
                .findById(3L)
                .orElseThrow();
        Course course1 = courseRepository
                .findById(1L)
                .orElseThrow();
        Course course2 = courseRepository
                .findById(2L)
                .orElseThrow();
        Course course3 = courseRepository
                .findById(3L)
                .orElseThrow();


        Enrollment enrollment1 = new Enrollment(student1,course1);
        Enrollment enrollment2 = new Enrollment(student2,course2);
        Enrollment enrollment3 = new Enrollment(student3,course3);


        enrollmentRepository.save(enrollment1);
        enrollmentRepository.save(enrollment2);
        enrollmentRepository.save(enrollment3);
    }
}