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
                .findById(enrollment.getStudentId())
                .orElseThrow(() ->
                        new StudentNotFoundException(enrollment.getStudentId()));

        Course course = courseRepository
                .findById(enrollment.getCourseId())
                .orElseThrow(() ->
                        new CourseNotFoundException(enrollment.getCourseId()));

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return toDTO(savedEnrollment, student, course);
    }

    // CONSULTAR
    public EnrollmentDTO findById(Long id) {

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
    public EnrollmentDTO cancel(Long id) {

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
    public void delete(Long id) {

        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }

        enrollmentRepository.deleteById(id);
    }

    // CONVERTIR ENROLLMENT → DTO
    private EnrollmentDTO toDTO(Enrollment enrollment) {

        Student student = studentRepository
                .findById(enrollment.getStudentId())
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                enrollment.getStudentId()));

        Course course = courseRepository
                .findById(enrollment.getCourseId())
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                enrollment.getCourseId()));

        return new EnrollmentDTO(
                enrollment.getId(),
                student.getFirstName() + " " + student.getLastName(),
                course.getName(),
                enrollment.getEnrollmentDate(),
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
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }
}