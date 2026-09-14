package co.edu.cesde.application.exception;

public class SudentEmailAlreadyExistsExeption extends BusinessException{
    public SudentEmailAlreadyExistsExeption(String message) {
        super("Email already exists: " + message);
    }
}
