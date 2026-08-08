package co.edu.cesde.infrastructure.persistence.inmemory;

import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.domain.models.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentInMemoryRepository implements EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public Enrollment save(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    @Override
    public boolean existsById(Long id) {
        return enrollments.stream().anyMatch(e -> e.getId().equals(id));

    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream().filter(e -> e.getId().equals(id)).findFirst();

    }

    @Override
    public List<Enrollment> findAll() {
        return enrollments;
    }

    @Override
    public void deleteById(Long id) {
        enrollments.removeIf(e -> e.getId().equals(id));

    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId().equals(enrollment.getId())) {
                enrollments.set(i, enrollment);
                return enrollment;
            }
        }
        return null;
    }



}
