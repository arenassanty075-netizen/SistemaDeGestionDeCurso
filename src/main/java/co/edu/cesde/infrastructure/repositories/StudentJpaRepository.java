package co.edu.cesde.infrastructure.repositories;

import co.edu.cesde.domain.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student,Long> {

    void deleteById(Long StudentId);

}
