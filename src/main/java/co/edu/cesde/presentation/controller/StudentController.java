package co.edu.cesde.presentation.controller;

import co.edu.cesde.application.dto.request.CreateStudentDto;
import co.edu.cesde.application.dto.response.CreateStudentResponseDto;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.application.exception.SudentEmailAlreadyExistsExeption;
import co.edu.cesde.application.service.StudentService;
import co.edu.cesde.domain.models.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET - LISTAR ESTUDIANTES
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getStudents() {

        var students = studentService.findAll();

        List<CreateStudentResponseDto> response = new ArrayList<>();

        for (Student student : students) {
            response.add(new CreateStudentResponseDto(
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getEnrollmentStatus()
            ));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // GET - BUSCAR ESTUDIANTE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(
            @PathVariable Long id) {

        try {

            var studentOptional = studentService.findById(id);

            Student student = studentOptional.get();

            CreateStudentResponseDto response = new CreateStudentResponseDto(
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getEnrollmentStatus()
            );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        } catch (StudentNotFoundException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // POST - CREAR ESTUDIANTE
    @PostMapping
    public ResponseEntity<?> save(
            @Valid @RequestBody CreateStudentDto student) {

        try {

            Student newStudent = new Student(
                    student.firstName(),
                    student.lastName(),
                    student.email(),
                    student.birthDate()
            );

            var createdStudent = studentService.save(newStudent);

            CreateStudentResponseDto response = new CreateStudentResponseDto(
                    createdStudent.getStudentId(),
                    createdStudent.getFirstName(),
                    createdStudent.getLastName(),
                    createdStudent.getEmail(),
                    createdStudent.getEnrollmentStatus()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (SudentEmailAlreadyExistsExeption e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    // PUT - ACTUALIZAR ESTUDIANTE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        try {

            student.setStudentId(id);

            var updatedStudent = studentService.update(student);

            CreateStudentResponseDto response = new CreateStudentResponseDto(
                    updatedStudent.getStudentId(),
                    updatedStudent.getFirstName(),
                    updatedStudent.getLastName(),
                    updatedStudent.getEmail(),
                    updatedStudent.getEnrollmentStatus()
            );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        } catch (StudentNotFoundException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (SudentEmailAlreadyExistsExeption e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    // DELETE - ELIMINAR ESTUDIANTE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable Long id) {

        try {

            studentService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        } catch (StudentNotFoundException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}


