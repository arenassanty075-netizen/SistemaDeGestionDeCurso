package co.edu.cesde.infrastructure.persistence.inmemory;

import co.edu.cesde.application.Repository.CourseRepository;

import co.edu.cesde.domain.models.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseInMemoryRepository implements CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    @Override
    public Course save(Course course){
        courses.add(course);
        return course;
    }

    @Override
    public  Boolean existsById(Long id){
        return courses.stream().anyMatch(course -> course.getId().equals(id));
    }

    @Override
    public Optional<Course> findById(Long id){
        return courses.stream().filter(course -> course.getId().equals(id)).findFirst();

    }

    @Override
    public List<Course> findAll(){
        return courses;
    }

    @Override
    public void  deleteById(Long id){
        courses.removeIf(course -> course.getId().equals(id));

    }

    @Override
    public Course update(Course course){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getId().equals(course.getId())){
                courses.set(i, course);
                return course;
            }
        }
        return null;
    }




}
