package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.course.Course;

import java.util.List;

public interface CourseService extends BaseCrudService<Course> {

    List<Course> findByTeacherId(Long teacherId);
}
