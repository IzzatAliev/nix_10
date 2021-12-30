package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService extends BaseService<Course> {

    Map<Long, String> findByStudentId(Long studentId);
    List<Course> findAllByStudentId(Long studentId);
}
