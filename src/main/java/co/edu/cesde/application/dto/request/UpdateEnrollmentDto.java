package co.edu.cesde.application.dto.request;

import co.edu.cesde.domain.models.EnrollmentStatus;

public record UpdateEnrollmentDto(
        Long id,
        Long studentId,
        Long courseId,
        EnrollmentStatus status
) {
}
