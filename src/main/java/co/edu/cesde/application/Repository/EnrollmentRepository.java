package co.edu.cesde.application.Repository;
import co.edu.cesde.domain.models.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    boolean existsById(String id);
    Optional<Enrollment> findById(String id);
    void deleteById(String id);
    Enrollment update(Enrollment enrollment);
    List<Enrollment> findAll();


}
