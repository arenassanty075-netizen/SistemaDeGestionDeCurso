package co.edu.cesde.presentation.controller;

import co.edu.cesde.application.service.CourseService;
import co.edu.cesde.domain.models.Course;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.findAll();

    }

    @GetMapping("/{id}")
    public Optional<Course>  getCourseById(@PathVariable Long id){
        return courseService.findById(id);

    }
    @PostMapping
    public Course save(@RequestBody Course course){
        return courseService.save(course);
    }

    @PutMapping
    public Course update(@RequestBody Course course){
        return courseService.update(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        courseService.deleteById(id);
    }






}
