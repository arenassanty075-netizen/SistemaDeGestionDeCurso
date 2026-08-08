package co.edu.cesde.application.service;

import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.application.dto.StudentDTO;
import co.edu.cesde.application.exception.StudentNotFoundException;
import co.edu.cesde.domain.models.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO save(StudentDTO studentDTO) {

        String[] names = studentDTO.getFullName().trim().split(" ", 2);
        String firstName = names[0];
        String lastName = names.length > 1 ? names[1] : "";

        Student student = new Student(
                studentDTO.getId(),
                firstName,
                lastName,
                studentDTO.getEmail(),
                studentDTO.getBirthDate()
        );

        Student savedStudent = studentRepository.save(student);

        return toDTO(savedStudent);
    }

    public StudentDTO findById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return toDTO(student);
    }

    public List<StudentDTO> findAll() {

        return studentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO update(StudentDTO studentDTO) {

        String[] names = studentDTO.getFullName().trim().split(" ", 2);
        String firstName = names[0];
        String lastName = names.length > 1 ? names[1] : "";

        Student student = new Student(
                studentDTO.getId(),
                firstName,
                lastName,
                studentDTO.getEmail(),
                studentDTO.getBirthDate()
        );

        Student updatedStudent = studentRepository.update(student);

        if (updatedStudent == null) {
            throw new StudentNotFoundException(studentDTO.getId());
        }

        return toDTO(updatedStudent);
    }

    public void delete(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);
    }

    private StudentDTO toDTO(Student student) {

        String fullName = student.getFirstName() + " " + student.getLastName();

        return new StudentDTO(
                student.getStudentId(),
                fullName,
                student.getEmail(),
                student.getBirthDate()
        );
    }
}