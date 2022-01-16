package ua.com.alevel.service.course;

import ua.com.alevel.persistence.entity.course.Course;
import ua.com.alevel.persistence.entity.user.Teacher;
import ua.com.alevel.service.BaseCrudService;

import java.util.List;

public interface CourseService extends BaseCrudService<Course> {

    List<Course> findByTeacherId(Long teacherId);
    List<Course> findByTeacher(Teacher teacher);
}
