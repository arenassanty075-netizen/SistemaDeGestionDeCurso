package co.edu.cesde.presentation.controller;

import co.edu.cesde.application.exception.StudentAlreadyExistsExeption;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.application.service.StudentService;
import co.edu.cesde.domain.models.Student;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        try {
            return studentService.findAll();

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Optional<Student> findById(@PathVariable Long id) {
        try {
            return studentService.findById(id);

        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException(id);
        }
    }

    @PostMapping
    public Student save(@RequestBody Student student) {

        try {
            return studentService.save(student);

        }catch (ConstraintViolationException e) {

            if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }

            if (student.getLastName() == null || student.getLastName().isBlank()) {
                throw new IllegalArgumentException("El apellido no puede estar vacío");
            }

            if (student.getEmail() == null || !student.getEmail().contains("@")) {
                throw new IllegalArgumentException("El correo debe tener un formato válido");
            }

            throw new IllegalArgumentException("Los datos del estudiante no son válidos");
        }
    }


    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        try {
            student.setStudentId(id);
            return studentService.update(student);

        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException(id);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        try {
            studentService.deleteById(id);

        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException(id);
        }
    }
}