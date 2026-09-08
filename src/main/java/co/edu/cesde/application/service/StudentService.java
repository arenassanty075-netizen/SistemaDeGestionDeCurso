package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.domain.models.Student;
import co.edu.cesde.infrastructure.repositories.StudentJpaRepository;
import org.springframework.stereotype.Service;

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
        return studentRepository.save(student);
    }

    // CONSULTAR POR ID
    @Override
    public Optional<Student> findById(Long studentId) {
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

        if (!studentRepository.existsById(student.getStudentId())) {
            throw new StudentNotFoundException(student.getStudentId());
        }

        return studentRepository.save(student);
    }

    // EXISTE
    @Override
    public boolean existsById(Long studentId) {
        return studentRepository.existsById(studentId);
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