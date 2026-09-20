package co.edu.cesde.application.dto.response;

public record CreateCourseResponseDto(
        Long id,
        String code,
        String name,
        String description,
        Integer maxCapacity
) {
}
