package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentRepository extends BaseRepository<Student> {

    @Query(value = "select s from Student as s join s.courses as sc where sc in :courseId")
    Map<Long, String> findByCourseId(Long courseId);

    @Query(value = "select s from Student as s join s.courses as sc where sc in :courseId")
    List<Student> findAllByCourseId(Long courseId);
}
