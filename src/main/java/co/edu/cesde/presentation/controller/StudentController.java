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
    public ResponseEntity<Object> getStudent(
            @PathVariable Long id) {

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


    }

    // POST - CREAR ESTUDIANTE
    @PostMapping
    public ResponseEntity<Object> save(
            @Valid @RequestBody CreateStudentDto student) {


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

    }

    // PUT - ACTUALIZAR ESTUDIANTE
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {



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


    }

    // DELETE - ELIMINAR ESTUDIANTE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(
            @PathVariable Long id) {

           var studentOptional = studentService.findById(id);

            studentService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();


    }
}


