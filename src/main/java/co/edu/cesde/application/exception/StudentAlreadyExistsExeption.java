package co.edu.cesde.application.exception;

public class StudentAlreadyExistsExeption extends ResourceAlreadyExistsException {
    public StudentAlreadyExistsExeption(String message) {
        super(message);
    }
}
