package co.edu.cesde.application.exception;

public class StudentNotFoundException extends ResouceNotFoundException {

    public StudentNotFoundException(Long studentId) {
        super("No existe un estudiante con el id: " + studentId);
    }
}