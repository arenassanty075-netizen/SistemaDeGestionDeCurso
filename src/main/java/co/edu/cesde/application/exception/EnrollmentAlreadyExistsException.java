package co.edu.cesde.application.exception;

public class EnrollmentAlreadyExistsException extends BusinessException {

    public EnrollmentAlreadyExistsException(Long studentId, Long courseId) {
        super("El estudiante con ID " + studentId +
                " ya está matriculado en el curso con ID " + courseId);
    }
}