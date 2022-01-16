package ua.com.alevel.persistence.repository.course;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.course.Course;
import ua.com.alevel.persistence.entity.user.Teacher;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface CourseRepository extends BaseRepository<Course> {

    List<Course> findByTeacherId(Long teacherId);
    List<Course> findByTeacher(Teacher teacher);
}
