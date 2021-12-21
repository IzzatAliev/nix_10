package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService extends BaseService<Student> {

    Map<Long, String> findByCourseId(Long courseId);
    List<Student> findAllByCourseId(Long courseId);
}
