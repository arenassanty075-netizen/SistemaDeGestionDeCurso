package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.application.dto.EnrollmentDTO;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.application.exception.EnrollmentNotFoundException;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.domain.models.Course;
import co.edu.cesde.domain.models.Enrollment;
import co.edu.cesde.domain.models.EnrollmentStatus;
import co.edu.cesde.domain.models.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREAR INSCRIPCIÓN
    public EnrollmentDTO save(Enrollment enrollment) {


        Student student = studentRepository
                .findById(enrollment.getStudent().getStudentId())
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                enrollment.getStudent().getStudentId()));

        Course course = courseRepository
                .findById(enrollment.getCourse().getId())
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                enrollment.getCourse().getId()));

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return toDTO(savedEnrollment);
    }

    // CONSULTAR
    public EnrollmentDTO findById(String id) {

        Enrollment enrollment = enrollmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(id));

        return toDTO(enrollment);
    }

    // LISTAR
    public List<EnrollmentDTO> findAll() {

        return enrollmentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // CANCELAR INSCRIPCIÓN
    public EnrollmentDTO cancel(String id) {

        Enrollment enrollment = enrollmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(id));

        enrollment.setStatus(EnrollmentStatus.CANCELLED);

        Enrollment updatedEnrollment =
                enrollmentRepository.update(enrollment);

        return toDTO(updatedEnrollment);
    }

    // ELIMINAR
    public void delete(String id) {

        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }

        enrollmentRepository.deleteById(id);
    }

    // CONVERTIR ENROLLMENT → DTO
    private EnrollmentDTO toDTO(Enrollment enrollment) {

        Student student = enrollment.getStudent();
        Course course = enrollment.getCourse();

        return new EnrollmentDTO(
                enrollment.getId(),
                student.getFirstName() + " " + student.getLastName(),
                course.getName(),
                enrollment.getCreatedAt(),
                enrollment.getStatus()
        );
    }

    // CONVERTIR ENROLLMENT → DTO
    private EnrollmentDTO toDTO(
            Enrollment enrollment,
            Student student,
            Course course) {

        return new EnrollmentDTO(
                enrollment.getId(),
                student.getFirstName() + " " + student.getLastName(),
                course.getName(),
                enrollment.getCreatedAt(),
                enrollment.getStatus()
        );
    }
}