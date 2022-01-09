package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Student;

@Repository
public interface StudentRepository extends UserRepository<Student> {
}
