package co.edu.cesde.presentation.controller;

import co.edu.cesde.application.exception.CourseCodeAlreadyExistsException;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.application.service.CourseService;
import co.edu.cesde.domain.models.Course;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {

        try {
            return courseService.findAll();

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {

        try {
            return courseService.findById(id);

        } catch (CourseNotFoundException e) {
            throw new CourseNotFoundException(id);
        }
    }
    @PostMapping
    public Course save( @RequestBody Course course) {

        try {
            return courseService.save(course);

        } catch (CourseCodeAlreadyExistsException e) {

            throw new CourseCodeAlreadyExistsException(course.getCode());

        } catch (ConstraintViolationException e) {

            if (course.getCode() == null || course.getCode().isBlank()) {
                throw new IllegalArgumentException(
                        "El código del curso no puede estar vacío"
                );
            }

            if (course.getName() == null || course.getName().isBlank()) {
                throw new IllegalArgumentException(
                        "El nombre del curso no puede estar vacío"
                );
            }

            if (course.getDescription() == null || course.getDescription().isBlank()) {
                throw new IllegalArgumentException(
                        "La descripción del curso no puede estar vacía"
                );
            }

            if (course.getMaxCapacity() == null) {
                throw new IllegalArgumentException(
                        "La capacidad máxima del curso es obligatoria"
                );
            }

            if (course.getMaxCapacity() < 15) {
                throw new IllegalArgumentException(
                        "La capacidad mínima del curso debe ser de 15 estudiantes"
                );
            }

            if (course.getMaxCapacity() > 30) {
                throw new IllegalArgumentException(
                        "La capacidad máxima del curso debe ser de 30 estudiantes"
                );
            }

            throw new IllegalArgumentException(
                    "Los datos del curso no son válidos"
            );
        }
    }



    @PutMapping("/{id}")
    public Course update(
            @PathVariable Long id,
            @RequestBody Course course) {

        try {

            course.setId(id);

            return courseService.update(course);

        } catch (CourseNotFoundException e) {

            throw new CourseNotFoundException(id);

        } catch (CourseCodeAlreadyExistsException e) {

            throw new CourseCodeAlreadyExistsException(course.getCode());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        try {

            courseService.deleteById(id);

        } catch (CourseNotFoundException e) {

            throw new CourseNotFoundException(id);
        }
    }






}
