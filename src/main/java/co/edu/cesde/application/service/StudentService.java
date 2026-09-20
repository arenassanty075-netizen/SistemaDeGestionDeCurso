package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.application.exception.StudentAlreadyExistsExeption;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.application.exception.SudentEmailAlreadyExistsExeption;
import co.edu.cesde.domain.models.Student;
import co.edu.cesde.infrastructure.repositories.StudentJpaRepository;
import org.springframework.stereotype.Service;
import co.edu.cesde.application.dto.request.CreateStudentDto;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentRepository {

    private final StudentJpaRepository studentRepository;

    public StudentService(StudentJpaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREAR
    @Override
    public Student save(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("El estudiante no puede ser null");
        }

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new SudentEmailAlreadyExistsExeption(
                    "Ya existe un estudiante con el email: " + student.getEmail()
            );
        }

        return studentRepository.save(student);
    }

    // CONSULTAR POR ID
    @Override
    public Optional<Student> findById(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }
        return studentRepository.findById(studentId);

    }

    // LISTAR
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


// ACTUALIZAR
    @Override
    public Student update(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("El estudiante no puede ser null");
        }

        if (!studentRepository.existsById(student.getStudentId())) {
            throw new StudentNotFoundException(student.getStudentId());
        }

        if (studentRepository.existsByEmailAndStudentIdNot(
                student.getEmail(),
                student.getStudentId())) {

            throw new SudentEmailAlreadyExistsExeption(
                    "Ya existe un estudiante con el email: " + student.getEmail()
            );
        }

        return studentRepository.save(student);
    }



    // EXISTE POR ID
    @Override
    public boolean existsById(Long studentId) {
        return studentRepository.existsById(studentId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    // ELIMINAR
    @Override
    public void deleteById(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        studentRepository.deleteById(studentId);
    }
}