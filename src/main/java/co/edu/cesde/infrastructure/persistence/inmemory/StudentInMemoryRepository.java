package co.edu.cesde.infrastructure.persistence.inmemory;

import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.domain.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentInMemoryRepository implements StudentRepository {

    private final List<Student> students  = new ArrayList<>();

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }


    @Override
    public Optional<Student> findById(Long StudentId){
        return students.stream().filter(student -> student.getStudentId().equals(StudentId)).findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public  Student update(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(student.getStudentId())) {
                students.set(i, student);
                return  student;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long StudentId) {
        students.removeIf(student -> student.getStudentId().equals(StudentId));
    }

    @Override
    public boolean existsById(Long StudentId) {
        return students.stream().anyMatch(student -> student.getStudentId().equals(StudentId));
    }
}
