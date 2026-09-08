package co.edu.cesde.infrastructure.repositories;

import co.edu.cesde.domain.models.Course;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository  extends JpaRepository<Course,Long> {

}
