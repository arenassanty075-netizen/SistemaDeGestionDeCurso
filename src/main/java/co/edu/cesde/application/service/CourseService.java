package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.CourseRepository;
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

        return courseRepository.findById(id);

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