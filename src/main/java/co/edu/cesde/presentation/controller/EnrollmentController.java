package co.edu.cesde.presentation.controller;

import co.edu.cesde.application.exception.BusinessException;
import co.edu.cesde.application.service.EnrollmentService;
import co.edu.cesde.domain.models.Enrollment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.findAll();

    }

    @GetMapping("/{id}")
    public Optional<Enrollment> getEnrollmentById(@PathVariable Long id){
      return enrollmentService.findById(id);
    }

    @PostMapping
    public Enrollment createEnrollment(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {

        try {
            return enrollmentService.createEnrollment(studentId, courseId);

        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PutMapping
    public Enrollment update(@RequestBody Enrollment enrollment){
        return enrollmentService.update(enrollment);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        enrollmentService.deleteById(id);
    }
}
