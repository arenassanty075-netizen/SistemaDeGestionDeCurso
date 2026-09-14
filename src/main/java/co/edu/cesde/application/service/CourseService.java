package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.exception.CourseCodeAlreadyExistsException;
import co.edu.cesde.application.exception.CourseNotFoundException;
import co.edu.cesde.domain.models.Course;
import co.edu.cesde.infrastructure.repositories.CourseJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements CourseRepository {

    private final CourseJpaRepository courseRepository;

    public CourseService(CourseJpaRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // CREAR
    @Override
    public Course save(Course course) {

        if (courseRepository.existsByCode(course.getCode())) {
            throw new CourseCodeAlreadyExistsException(course.getCode());
        }

        return courseRepository.save(course);
    }

    // VERIFICAR SI EXISTE
    @Override
    public Boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    // CONSULTAR
    @Override
    public Optional<Course> findById(Long id) {

        Optional<Course> course = courseRepository.findById(id);

        if (course.isEmpty()) {
            throw new CourseNotFoundException(id);
        }

        return course;
    }

    // LISTAR
    @Override
    public List<Course> findAll() {

        return courseRepository.findAll();
    }

    // ACTUALIZAR
    @Override
    public Course update(Course course) {

        if (!courseRepository.existsById(course.getId())) {
            throw new CourseNotFoundException(course.getId());
        }

        if (courseRepository.existsByCodeAndIdNot(
                course.getCode(),
                course.getId())) {

            throw new CourseCodeAlreadyExistsException(course.getCode());
        }

        return courseRepository.save(course);
    }

    // ELIMINAR
    @Override
    public void deleteById(Long id) {

        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        courseRepository.deleteById(id);
    }
}