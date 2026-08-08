package co.edu.cesde.presentation;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.infrastructure.DataInitializer;
import co.edu.cesde.infrastructure.persistence.inmemory.StudentInMemoryRepository;
import co.edu.cesde.infrastructure.persistence.inmemory.CourseInMemoryRepository;
import co.edu.cesde.infrastructure.persistence.inmemory.EnrollmentInMemoryRepository;
import co.edu.cesde.application.service.StudentService;
import co.edu.cesde.application.service.CourseService;
import co.edu.cesde.application.service.EnrollmentService;

public class Main {

    public static void main(String[] args) {

        StudentRepository studentRepository =
                new StudentInMemoryRepository();

        CourseRepository courseRepository =
                new CourseInMemoryRepository();

        EnrollmentRepository enrollmentRepository =
                new EnrollmentInMemoryRepository();

        DataInitializer dataInitializer =
                new DataInitializer(
                        studentRepository,
                        courseRepository,
                        enrollmentRepository
                );
        dataInitializer.initialize();

        StudentService studentService =
                new StudentService(studentRepository);

        CourseService courseService =
                new CourseService(courseRepository);

        EnrollmentService enrollmentService =
                new EnrollmentService(
                        enrollmentRepository,
                        studentRepository,
                        courseRepository
                );





        studentRepository.findAll().forEach(student ->
                System.out.println(student)
        );



        courseRepository.findAll().forEach(course ->
                System.out.println(
                        course.getId() + " | " +
                                course.getCode() + " | " +
                                course.getName()
                )
        );

        System.out.println("\n=== ENROLLMENTS INICIALES ===");

        enrollmentRepository.findAll().forEach(enrollment ->
                System.out.println(
                        enrollment.getId() + " | Student " +
                                enrollment.getStudentId() + " | Course " +
                                enrollment.getCourseId() + " | " +
                                enrollment.getStatus()
                )
        );

        mostrarMenuPrincipal();


    }

    private static void mostrarMenuPrincipal() {

        System.out.println("=================================");
        System.out.println(" SISTEMA DE GESTIÓN DE CURSOS");
        System.out.println("=================================");
        System.out.println("1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Enrollments");
        System.out.println("0. Exit");
    }

    private static void mostrarMenuEstudiantes() {

        System.out.println("\n===== MENÚ ESTUDIANTES =====");
        System.out.println("1. Crear");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todos");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar");
        System.out.println("0. Volver");

    }

    private static void showCourseMenu() {

        System.out.println("\n===== MENÚ CURSOS =====");
        System.out.println("1. Crear");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todos");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar");
        System.out.println("0. Volver");

    }

    private static void showEnrollmentMenu() {

        System.out.println("\n===== MENÚ MATRÍCULAS =====");
        System.out.println("1. Crear matrícula");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todas");
        System.out.println("4. Cancelar matrícula");
        System.out.println("5. Eliminar matrícula");
        System.out.println("0. Volver");

    }
}
