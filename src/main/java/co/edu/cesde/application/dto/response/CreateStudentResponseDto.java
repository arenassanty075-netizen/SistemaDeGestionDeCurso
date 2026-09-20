package co.edu.cesde.application.dto.response;

import co.edu.cesde.domain.models.EnrollmentStatus;

public record CreateStudentResponseDto (
        Long studentId,
        String firstName,
        String lastName,
        String email,
        EnrollmentStatus enrollmentStatus

) {
}

