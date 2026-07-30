package co.edu.cesde.domain.models;

import java.time.LocalDate;

public class Student {
    Long StudentId;
    String firstName;
    String lastName;
    String email;
    LocalDate birthDate;

    public Student(Long studentId, String firstName, String lastName, String email, LocalDate birthDate) {
        StudentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Student() {
    }

    public Long getStudentId() {
        return StudentId;
    }

    public void setStudentId(Long studentId) {
        StudentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "=== STUDENT INFO ===\n" +
                "ID: " + getStudentId() + "\n" +
                "Nombre: " + getFirstName() + " " + getLastName() + "\n" +
                "Fecha Nacimiento: " + birthDate + "\n" ;

    }

}