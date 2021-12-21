package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao extends BaseDao<Student> {

    Map<Long, String> findByCourseId(Long courseId);
    List<Student> findAllByCourseId(Long courseId);
}
