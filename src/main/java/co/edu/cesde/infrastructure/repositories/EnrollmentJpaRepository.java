package co.edu.cesde.infrastructure.repositories;

import co.edu.cesde.domain.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<Enrollment,Long> {
}
