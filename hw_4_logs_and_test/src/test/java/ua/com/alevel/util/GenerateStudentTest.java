package ua.com.alevel.util;

import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

public class GenerateStudentTest {

    private static final StudentService studentService = new StudentService();

    public static final String NAME = "nameStudent";

    public static Student generateRandomStudent() {
        Student student = new Student();
        student.setName(NAME);
        return student;
    }

    public static Student generateStudent(String name) {
        Student student = new Student();
        student.setName(name);
        return student;
    }

    public static Student getRandomStudent() {
        return studentService.findAllStudents().getRandom();
    }
}