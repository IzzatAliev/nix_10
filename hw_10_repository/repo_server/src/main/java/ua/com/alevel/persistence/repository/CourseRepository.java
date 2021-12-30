package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Course;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseRepository extends BaseRepository<Course> {

    @Query(value = "select c from Course as c join c.students as cs where cs in :studentId")
    Map<Long, String> findByStudentId(Long studentId);

    @Query(value = "select c from Course as c join c.students as cs where cs in :studentId")
    List<Course> findAllByStudentId(Long studentId);
}
