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
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {

        try {

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

        } catch (CourseNotFoundException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save( @Valid @RequestBody CreateCourseDto course) {

        try {
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


        } catch (CourseCodeAlreadyExistsException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Course course) {

        try {

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

        } catch (CourseNotFoundException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (CourseCodeAlreadyExistsException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {

            courseService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        } catch (CourseNotFoundException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }








}
