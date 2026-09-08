package co.edu.cesde.presentation.controller;

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
    public Enrollment save(@RequestBody Enrollment enrollment){
        return enrollmentService.save(enrollment);
    }

    @PutMapping
    public Enrollment update(@RequestBody Enrollment enrollment){
        return enrollmentService.save(enrollment);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        enrollmentService.deleteById(id);
    }
}
