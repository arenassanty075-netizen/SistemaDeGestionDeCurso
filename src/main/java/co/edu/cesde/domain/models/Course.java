package co.edu.cesde.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@NoArgsConstructor



public class Course {
    @Id
    @Column(name = "course_id_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "course_code",unique = true, nullable = false)
    private String code;

    @NotBlank
    @Column(name = "course_name",nullable = false,length = 100 )
    private String name;

    @NotBlank
    @Column(name = "course_description")
    private String description;

    @NotBlank
    @NotNull
    @Min(value = 15,message = "Minima capacidad del curso debe ser al menos 15")
    @Max(value = 30,message = "Maxima capacidad del curso debe ser como maximo 30")
    @Column(name = "course_max_capacidad")
    private Integer maxCapacity;

    @Column(name = "course_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "course_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Course( String code, String name, String description, Integer maxCapacity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
