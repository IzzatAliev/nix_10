package ua.com.alevel.util;

import ua.com.alevel.entity.Course;
import ua.com.alevel.service.CourseService;

public class GenerateCourseTest {

    private static final CourseService courseService = new CourseService();

    public static final String NAME = "nameCourse";
    public static final int CREDIT = 5;

    public static Course generateRandomCourse() {
        Course course = new Course();
        course.setCourseName(NAME);
        course.setCredit(CREDIT);
        return course;
    }

    public static Course generateCourse(String courseName, int credit) {
        Course course = new Course();
        course.setCourseName(courseName);
        return course;
    }

    public static Course getRandomCourse() {
        return courseService.findAllCourses().getRandom();
    }
}