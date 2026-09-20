package co.edu.cesde.application.dto.response;

import co.edu.cesde.domain.models.Course;
import co.edu.cesde.domain.models.EnrollmentStatus;
import co.edu.cesde.domain.models.Student;

public record CreateEnrollmentResponseDto(
        Long id,
        Student student,
        Course course,
        EnrollmentStatus status
) {
}
