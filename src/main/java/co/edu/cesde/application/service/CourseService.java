package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.dto.CourseDTO;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.domain.models.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // CREAR
    public CourseDTO save(CourseDTO courseDTO) {

        Course course = new Course(
                courseDTO.getId(),
                courseDTO.getCode(),
                courseDTO.getName(),
                courseDTO.getDescription(),
                courseDTO.getMaxCapacity()
        );

        Course savedCourse = courseRepository.save(course);

        return toDTO(savedCourse);
    }

    // CONSULTAR
    public CourseDTO findById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return toDTO(course);
    }

    // LISTAR
    public List<CourseDTO> findAll() {

        return courseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ACTUALIZAR
    public CourseDTO update(CourseDTO courseDTO) {

        Course course = new Course(
                courseDTO.getId(),
                courseDTO.getCode(),
                courseDTO.getName(),
                courseDTO.getDescription(),
                courseDTO.getMaxCapacity()
        );

        if (!courseRepository.existsById(courseDTO.getId())) {
            throw new CourseNotFoundException(courseDTO.getId());
        }

        Course updatedCourse = courseRepository.update(course);

        return toDTO(updatedCourse);
    }

    // ELIMINAR
    public void delete(Long id) {

        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        courseRepository.deleteById(id);
    }

    // CONVERTIR COURSE → COURSE DTO
    private CourseDTO toDTO(Course course) {

        return new CourseDTO(
                course.getId(),
                course.getCode(),
                course.getName(),
                course.getDescription(),
                course.getMaxCapacity()
        );
    }
}