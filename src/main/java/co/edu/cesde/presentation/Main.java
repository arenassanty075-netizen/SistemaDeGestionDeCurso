package co.edu.cesde.presentation;

import co.edu.cesde.application.Repository.CourseRepository;
import co.edu.cesde.application.Repository.EnrollmentRepository;
import co.edu.cesde.application.Repository.StudentRepository;
import co.edu.cesde.application.service.CourseService;
import co.edu.cesde.application.service.EnrollmentService;
import co.edu.cesde.application.service.StudentService;
import co.edu.cesde.infrastructure.DataInitializer;
import co.edu.cesde.infrastructure.persistence.inmemory.CourseInMemoryRepository;
import co.edu.cesde.infrastructure.persistence.inmemory.EnrollmentInMemoryRepository;
import co.edu.cesde.infrastructure.persistence.inmemory.StudentInMemoryRepository;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // ==========================================
        // 1. REPOSITORIOS
        // ==========================================

        StudentRepository studentRepository =
                new StudentInMemoryRepository();

        CourseRepository courseRepository =
                new CourseInMemoryRepository();

        EnrollmentRepository enrollmentRepository =
                new EnrollmentInMemoryRepository();


        // ==========================================
        // 2. DATOS INICIALES
        // ==========================================

        DataInitializer dataInitializer =
                new DataInitializer(
                        studentRepository,
                        courseRepository,
                        enrollmentRepository
                );

        dataInitializer.initialize();


        // ==========================================
        // 3. SERVICIOS
        // ==========================================

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


        // ==========================================
        // 4. MENÚ PRINCIPAL
        // ==========================================

        boolean continuar = true;

        while (continuar) {

            mostrarMenuPrincipal();

            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {

                case "1":
                    mostrarMenuEstudiantes(studentService);
                    break;

                case "2":
                    showCourseMenu(courseService);
                    break;

                case "3":
                    showEnrollmentMenu(enrollmentService);
                    break;

                case "0":
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }


    // ==========================================
    // MENÚ PRINCIPAL
    // ==========================================

    private static void mostrarMenuPrincipal() {

        System.out.println("\n=================================");
        System.out.println(" SISTEMA DE GESTIÓN DE CURSOS");
        System.out.println("=================================");
        System.out.println("1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Enrollments");
        System.out.println("0. Exit");
    }


    // ==========================================
    // MENÚ ESTUDIANTES
    // ==========================================

    private static void mostrarMenuEstudiantes(StudentService studentService) {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n===== MENÚ ESTUDIANTES =====");
            System.out.println("1. Crear");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Listar todos");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {

                case "1":
                    System.out.println("Crear estudiante");
                    break;

                case "2":
                    System.out.println("Buscar estudiante");
                    break;

                case "3":
                    studentService.findAll().forEach(System.out::println);
                    break;

                case "4":
                    System.out.println("Actualizar estudiante");
                    break;

                case "5":
                    System.out.println("Eliminar estudiante");
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    // ==========================================
    // MENÚ CURSOS
    // ==========================================

    private static void showCourseMenu(CourseService courseService) {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n===== MENÚ CURSOS =====");
            System.out.println("1. Crear");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Listar todos");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {

                case "1":
                    System.out.println("Crear curso");
                    break;

                case "2":
                    System.out.println("Buscar curso");
                    break;

                case "3":
                    courseService.findAll().forEach(course ->
                            System.out.println(
                                    course.getId() + " | " +
                                            course.getCode() + " | " +
                                            course.getName()
                            )
                    );
                    break;

                case "4":
                    System.out.println("Actualizar curso");
                    break;

                case "5":
                    System.out.println("Eliminar curso");
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    // ==========================================
    // MENÚ MATRÍCULAS
    // ==========================================

    private static void showEnrollmentMenu(
            EnrollmentService enrollmentService) {

        boolean volver = false;

        while (!volver) {

            System.out.println("\n===== MENÚ MATRÍCULAS =====");
            System.out.println("1. Crear matrícula");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Listar todas");
            System.out.println("4. Cancelar matrícula");
            System.out.println("5. Eliminar matrícula");
            System.out.println("0. Volver");

            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {

                case "1":
                    System.out.println("Crear matrícula");
                    break;

                case "2":
                    System.out.println("Buscar matrícula");
                    break;

                case "3":
                    enrollmentService.findAll().forEach(enrollment ->
                            System.out.println(
                                    enrollment.getId() + " | Student " +
                                            enrollment.getStudentId() + " | Course " +
                                            enrollment.getCourseId() + " | " +
                                            enrollment.getStatus()
                            )
                    );
                    break;

                case "4":
                    System.out.println("Cancelar matrícula");
                    break;

                case "5":
                    System.out.println("Eliminar matrícula");
                    break;

                case "0":
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}