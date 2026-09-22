package co.edu.cesde.presentation.controller;

import co.edu.cesde.application.dto.request.CreateCourseDto;
import co.edu.cesde.application.dto.response.CreateCourseResponseDto;
import co.edu.cesde.application.exception.CourseCodeAlreadyExistsException;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.application.service.CourseService;
import co.edu.cesde.domain.models.Course;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CreateCourseResponseDto>> getCourse() {

        var courses = courseService.findAll();
        List<CreateCourseResponseDto> response = new ArrayList<>();

        for (Course course : courses) {
            response.add(new CreateCourseResponseDto(
                    course.getId(),
                    course.getCode(),
                    course.getName(),
                    course.getDescription(),
                    course.getMaxCapacity()

            ));
        }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {



            var courseOptional = courseService.findById(id);
            Course course = courseOptional.get();

            CreateCourseResponseDto response = new CreateCourseResponseDto(
                    course.getId(),
                    course.getCode(),
                    course.getName(),
                    course.getDescription(),
                    course.getMaxCapacity()
            );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);


    }

    @PostMapping
    public ResponseEntity<Object> save( @Valid @RequestBody CreateCourseDto course) {


            Course newCourse = new Course(
                    course.code(),
                    course.name(),
                    course.description(),
                    course.maxCapacity()
            );
            var createdCourse = courseService.save(newCourse);
            CreateCourseResponseDto response = new CreateCourseResponseDto(
                    createdCourse.getId(),
                    createdCourse.getCode(),
                    createdCourse.getName(),
                    createdCourse.getDescription(),
                    createdCourse.getMaxCapacity()
            );
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);



    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody Course course) {



            course.setId(id);

            var updatedCourse = courseService.update(course);

            CreateCourseResponseDto response = new CreateCourseResponseDto(
                    updatedCourse.getId(),
                    updatedCourse.getCode(),
                    updatedCourse.getName(),
                    updatedCourse.getDescription(),
                    updatedCourse.getMaxCapacity()
            );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {


            var course = courseService.findById(id);
            courseService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();


    }








}
