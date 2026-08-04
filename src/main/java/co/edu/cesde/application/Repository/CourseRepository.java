package co.edu.cesde.application.Repository;

import co.edu.cesde.domain.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    Boolean existsById(Long id);
    Optional<Course> findById(Long id);
    List<Course> findAll();
    void deleteById(Long id);
    Course update(Course course);

}
