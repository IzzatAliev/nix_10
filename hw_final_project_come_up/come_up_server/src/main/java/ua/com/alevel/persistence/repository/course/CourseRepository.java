package ua.com.alevel.persistence.repository.course;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.course.Course;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface CourseRepository extends BaseRepository<Course> {
}
