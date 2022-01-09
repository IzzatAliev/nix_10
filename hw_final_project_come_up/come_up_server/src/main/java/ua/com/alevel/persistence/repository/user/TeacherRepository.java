package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Teacher;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {
}
