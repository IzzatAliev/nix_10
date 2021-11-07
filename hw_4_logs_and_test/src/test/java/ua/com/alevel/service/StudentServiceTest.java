package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.GenerateStudentTest;
import ua.com.alevel.util.MyArray;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest extends GenerateStudentTest {

    private static final StudentService studentService = new StudentService();

    private static final String NAME_UPDATE = "nameStudent_update";
    private static final int DEFAULT_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Student student = generateStudent(NAME + i);
            studentService.create(student);
        }
        Assertions.assertEquals(studentService.findAllStudents().size(), DEFAULT_SIZE);
    }

    @Test
    @Order(1)
    public void shouldBeCreateStudent() {
        Student student = generateRandomStudent();

        studentService.create(student);

        MyArray<Student> students = studentService.findAllStudents();
        Assertions.assertEquals(students.size(), DEFAULT_SIZE + 1);
    }

    @Test
    @Order(2)
    public void shouldBeUpdateStudentById() {
        Student student = getRandomStudent();
        student.setName(NAME_UPDATE);

        studentService.update(student);

        student = studentService.findStudentById(student.getId());

        Assertions.assertEquals(NAME_UPDATE, student.getName());
    }

    @Test
    @Order(3)
    public void shouldBeDeleteStudentById() {
        Student student = getRandomStudent();

        studentService.deleteStudent(student.getId());

        Assertions.assertEquals(studentService.findAllStudents().size(), DEFAULT_SIZE);
    }


}
