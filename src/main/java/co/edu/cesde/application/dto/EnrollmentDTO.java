package co.edu.cesde.application.dto;

import co.edu.cesde.domain.models.EnrollmentStatus;


import java.time.LocalDateTime;

public class EnrollmentDTO {
    private String id;
    private String studentId;
    private String courseId;
    private LocalDateTime createdAt;
    private EnrollmentStatus status;

    public EnrollmentDTO() {}

    public EnrollmentDTO(String id, String studentId, String courseId, LocalDateTime createdAt, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}

