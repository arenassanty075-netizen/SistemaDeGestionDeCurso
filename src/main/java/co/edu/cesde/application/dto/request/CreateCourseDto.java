package co.edu.cesde.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseDto(
        @NotBlank(message = "El código del curso no puede estar vacío")
        String code,
        @NotBlank(message = "El nombre del curso no puede estar vacío")
        String name,
        @NotBlank(message = "La descripción del curso no puede estar vacía")
        String description,
        @NotNull(message = "La capacidad del curso no puede estar vacía")
        @Min(value = 15, message = "Minima capacidad del curso debe ser al menos 15")
        @Max(value = 30, message = "Maxima capacidad del curso debe ser como maximo 30")
        Integer maxCapacity


) {
}
