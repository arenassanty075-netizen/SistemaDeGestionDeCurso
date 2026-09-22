package co.edu.cesde.application.exception;

public class CourseCodeAlreadyExistsException extends ResourceAlreadyExistsException {
    public CourseCodeAlreadyExistsException(String code) {
        super("El código del curso " + code + " ya existe");
    }
}
