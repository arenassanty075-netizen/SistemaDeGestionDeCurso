package co.edu.cesde.application.exception;

public class EnrollmentNotFoundException extends BusinessException {
    public EnrollmentNotFoundException(String id) {
        super("No existe una matrícula con el id: " + id);
    }
}
