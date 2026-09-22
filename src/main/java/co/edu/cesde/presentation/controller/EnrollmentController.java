package co.edu.cesde.presentation.controller;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.application.exception.EnrollmentAlreadyExistsException;
import co.edu.cesde.application.exception.EnrollmentNotFoundException;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.application.dto.request.CreateEnrollmentDto;
import co.edu.cesde.application.dto.response.CreateEnrollmentResponseDto;
import co.edu.cesde.domain.models.Course;
import co.edu.cesde.domain.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.edu.cesde.application.service.EnrollmentService;
import co.edu.cesde.domain.models.Enrollment;
import org.springframework.web.bind.annotation.*;
import co.edu.cesde.application.dto.request.UpdateEnrollmentDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnrollmentById(@PathVariable Long id) {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(enrollmentService.findById(id).get());


    }


    @PostMapping
    public ResponseEntity<Object> createEnrollment(
            @RequestBody CreateEnrollmentDto enrollmentDto) {

            Enrollment enrollment = enrollmentService.createEnrollment(
                    enrollmentDto.studentId(),
                    enrollmentDto.courseId()
            );

            CreateEnrollmentResponseDto response =
                    new CreateEnrollmentResponseDto(
                            enrollment.getId(),
                            enrollment.getStudent(),
                            enrollment.getCourse(),
                            enrollment.getStatus()
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);


    }






    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UpdateEnrollmentDto enrollmentDto) {

            Enrollment enrollment = new Enrollment();

            enrollment.setId(enrollmentDto.id());
            enrollment.setStatus(enrollmentDto.status());

            Student student = new Student();
            student.setStudentId(enrollmentDto.studentId());

            Course course = new Course();
            course.setId(enrollmentDto.courseId());

            enrollment.setStudent(student);
            enrollment.setCourse(course);

            Enrollment updatedEnrollment =
                    enrollmentService.update(enrollment);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedEnrollment);


    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {


            enrollmentService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();


    }

}
