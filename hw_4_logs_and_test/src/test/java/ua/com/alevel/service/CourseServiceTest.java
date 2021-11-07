package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Course;
import ua.com.alevel.util.GenerateCourseTest;
import ua.com.alevel.util.MyArray;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceTest extends GenerateCourseTest {

    private static final CourseService courseService = new CourseService();

    private static final String NAME_UPDATE = "nameCourse_update";
    private static final int DEFAULT_SIZE = 10;
    private static final int CREDIT_UPDATE = 7;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Course course = generateCourse(NAME + i, i);
            courseService.create(course);
        }
        Assertions.assertEquals(courseService.findAllCourses().size(), DEFAULT_SIZE);
    }

    @Test
    @Order(1)
    public void shouldBeCreateCourse() {
        Course course = generateRandomCourse();

        courseService.create(course);

        MyArray<Course> courses = courseService.findAllCourses();
        Assertions.assertEquals(courses.size(), DEFAULT_SIZE + 1);
    }

    @Test
    @Order(2)
    public void shouldBeUpdateCourseById() {
        Course course = getRandomCourse();
        course.setCourseName(NAME_UPDATE);
        course.setCredit(CREDIT_UPDATE);

        courseService.update(course);

        course = courseService.findCourseById(course.getId());

        Assertions.assertEquals(NAME_UPDATE, course.getCourseName());
        Assertions.assertEquals(CREDIT_UPDATE, course.getCredit());
    }

    @Test
    @Order(3)
    public void shouldBeDeleteCourseById() {
        Course course = getRandomCourse();

        courseService.deleteCourse(course.getId());

        Assertions.assertEquals(courseService.findAllCourses().size(), DEFAULT_SIZE);
    }
}
