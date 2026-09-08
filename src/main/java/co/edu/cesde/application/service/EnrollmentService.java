package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.application.exception.EnrollmentNotFoundException;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.domain.models.Course;
import co.edu.cesde.domain.models.Enrollment;
import co.edu.cesde.domain.models.EnrollmentStatus;
import co.edu.cesde.domain.models.Student;
import co.edu.cesde.infrastructure.repositories.CourseJpaRepository;
import co.edu.cesde.infrastructure.repositories.EnrollmentJpaRepository;
import co.edu.cesde.infrastructure.repositories.StudentJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentRepository;
    private final StudentJpaRepository studentRepository;
    private final CourseJpaRepository courseRepository;

    public EnrollmentService(
            EnrollmentJpaRepository enrollmentRepository,
            StudentJpaRepository studentRepository,
            CourseJpaRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREAR INSCRIPCIÓN
    @Override
    public Enrollment save(Enrollment enrollment) {

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

        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        enrollment.setCreatedAt(java.time.LocalDateTime.now());
        enrollment.setUpdatedAt(java.time.LocalDateTime.now());

        return enrollmentRepository.save(enrollment);
    }

    // CONSULTAR POR ID
    @Override
    public Optional<Enrollment> findById(Long id) {

        return enrollmentRepository.findById(id);
    }

    // LISTAR
    @Override
    public List<Enrollment> findAll() {

        return enrollmentRepository.findAll();
    }

    // VERIFICAR SI EXISTE
    @Override
    public boolean existsById(Long id) {

        return enrollmentRepository.existsById(id);
    }

    // ACTUALIZAR
    @Override
    public Enrollment update(Enrollment enrollment) {

        if (!enrollmentRepository.existsById(enrollment.getId())) {
            throw new EnrollmentNotFoundException(enrollment.getId());
        }

        return enrollmentRepository.save(enrollment);
    }

    // ELIMINAR
    @Override
    public void deleteById(Long id) {

        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }

        enrollmentRepository.deleteById(id);
    }

    // CANCELAR INSCRIPCIÓN
    public Enrollment cancel(Long id) {

        Enrollment enrollment = enrollmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(id));

        enrollment.setStatus(EnrollmentStatus.CANCELLED);

        return enrollmentRepository.save(enrollment);
    }
}