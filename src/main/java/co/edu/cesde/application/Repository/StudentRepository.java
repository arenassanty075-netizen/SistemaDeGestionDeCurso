package co.edu.cesde.application.Repository;

import co.edu.cesde.domain.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long studentId);
    List<Student> findAll();
    Student update(Student student);
    void deleteById(Long StudentId);
    boolean existsById(Long StudentId);




}
