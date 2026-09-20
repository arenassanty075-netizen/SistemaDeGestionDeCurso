package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.application.exception.EnrollmentAlreadyExistsException;
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

import java.time.LocalDateTime;
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
    public Enrollment createEnrollment(Long studentId, Long courseId) {

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(studentId));

        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() ->
                        new CourseNotFoundException(courseId));

        if (enrollmentRepository.existsByStudentStudentIdAndCourseId(studentId, courseId)) {
            throw new EnrollmentAlreadyExistsException(studentId, courseId);
        }


        Enrollment enrollment = new Enrollment(student, course);

        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollment.setCreatedAt(LocalDateTime.now());
        enrollment.setUpdatedAt(LocalDateTime.now());

        return enrollmentRepository.save(enrollment);
    }

    // CONSULTAR POR ID
    @Override
    public Optional<Enrollment> findById(Long id) {

        Optional<Enrollment> enrollment =
                enrollmentRepository.findById(id);

        if (enrollment.isEmpty()) {
            throw new EnrollmentNotFoundException(id);
        }

        return enrollment;
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

        Optional<Enrollment> enrollmentOptional =
                enrollmentRepository.findById(enrollment.getId());

        if (enrollmentOptional.isEmpty()) {
            throw new EnrollmentNotFoundException(enrollment.getId());
        }

        Enrollment enrollmentExistente = enrollmentOptional.get();

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
        if (enrollmentRepository.existsByStudentStudentIdAndCourseIdAndIdNot(
                student.getStudentId(),
                course.getId(),
                enrollment.getId())){
            throw new EnrollmentAlreadyExistsException(
                    student.getStudentId(),
                    course.getId()
            );
        }

        enrollmentExistente.setStudent(student);
        enrollmentExistente.setCourse(course);
        enrollmentExistente.setStatus(enrollment.getStatus());
        enrollmentExistente.setUpdatedAt(LocalDateTime.now());

        return enrollmentRepository.save(enrollmentExistente);
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
    // CANCELAR MATRÍCULA
    public Enrollment cancel(Long id) {

        Enrollment enrollment = enrollmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(id));

        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        enrollment.setUpdatedAt(LocalDateTime.now());

        return enrollmentRepository.save(enrollment);
    }

    // MÉTODO SAVE DE LA INTERFAZ
    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
    @Override
    public boolean existsByStudentStudentIdAndCourseId(Long studentId, Long courseId) {
        return enrollmentRepository.existsByStudentStudentIdAndCourseId(studentId, courseId);
    }

}