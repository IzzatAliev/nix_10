package ua.com.alevel.dao;

import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.MyArray;

public class StudentDao {

    public void create(Student student) {
        DBInMemory.getSample().createStudent(student);
    }

    public void update(Student student) {
        DBInMemory.getSample().updateStudent(student);
    }

    public void delete(String id) {
        DBInMemory.getSample().deleteStudent(id);
    }

    public Student findStudentById(String id) {
        return DBInMemory.getSample().findStudentById(id);
    }

    public MyArray<Student> findAllStudents() {
        return DBInMemory.getSample().findAllStudents();
    }

    public boolean existById(String id) {
        return DBInMemory.getSample().existStudentById(id);
    }
}
