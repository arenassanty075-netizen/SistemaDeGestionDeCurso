package co.edu.cesde.application.exception;

public class CourseNotFoundException extends ResouceNotFoundException {
    public CourseNotFoundException(Long id) {
        super("No existe un curso con el id: " + id);
    }
}
