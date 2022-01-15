//package ua.com.alevel.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.context.SpringBootTest;
//import ua.com.alevel.persistence.entity.user.Student;
//import ua.com.alevel.util.GenerateStudentTest;
//
//import java.util.Date;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class StudentServiceTest extends GenerateStudentTest {
//
//    private static final int DEFAULT_SIZE = 10;
//    private static final String email = "Bolik";
//    private static final Date date = new Date(System.currentTimeMillis());
//
//    private final StudentService studentService;
//
//    public StudentServiceTest(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @Test
//    public void shouldBeCreated() {
//        for (long i = 0; i<DEFAULT_SIZE; i++) {
//            Student student = generateRandomStudent(i,date,date,date, email + i);
//            studentService.create(student);
//        }
//        Assertions.assertEquals(studentService.findById(1l), 1);
//    }
//}
