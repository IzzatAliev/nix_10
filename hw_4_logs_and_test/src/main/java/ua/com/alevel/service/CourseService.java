package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.MyArray;

public class CourseService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final StudentDao studentDao = new StudentDao();
    private final CourseDao courseDao = new CourseDao();

    public void create(Course course) {
        LOGGER_INFO.info("create the new course: " + course.getCourseName());
        courseDao.create(course);
        Course someCourse = new Course();
        someCourse.setId(course.getId());
        someCourse.setCourseName(course.getCourseName());
        someCourse.setCredit(course.getCredit());
        MyArray<Course> c = new MyArray<>();
        c.add(someCourse);
        if (course.getStudentList() != null) {
            for (int i = 0; i < course.getStudentList().size(); i++) {
                course.getStudentList().get(i).setCourseList(c);
                Student student = course.getStudentList().get(i);
                if (!studentDao.existById(student.getId())) {
                    LOGGER_INFO.info("create the new student: " + student.getName());
                    studentDao.create(student);
                }
            }
        }
    }

    public void update(Course course) {
        LOGGER_INFO.info("update the course: " + course.getCourseName());
        if (courseDao.existById(course.getId())) {
            courseDao.update(course);
            String courseId = course.getId();
            course.setStudentList(courseDao.findCourseById(courseId).getStudentList());
            Course thisCourse = courseDao.findCourseById(courseId);
            if (thisCourse.getStudentList() != null) {
                for (int i = 0; i < thisCourse.getStudentList().size(); i++) {
                    String studentId = thisCourse.getStudentList().get(i).getId();
                    for (int j = 0; j < studentDao.findStudentById(studentId).getCourseList().size(); j++) {
                        if (studentDao.findStudentById(studentId).getCourseList().get(j).getId().equals(courseId))
                            studentDao.findStudentById(studentId).getCourseList().update(j, course);
                    }
                }
            }
        }
    }

    public void deleteCourse(String id) {
        LOGGER_WARN.warn("remove the course by id: " + id);
        if (courseDao.existById(id)) {
            Course thisCourse = courseDao.findCourseById(id);
            if (thisCourse.getStudentList() != null) {
                for (int i = 0; i < thisCourse.getStudentList().size(); i++) {
                    String studentId = thisCourse.getStudentList().get(i).getId();
                    for (int j = 0; j < studentDao.findStudentById(studentId).getCourseList().size(); j++) {
                        if (studentDao.findStudentById(studentId).getCourseList().get(j).getId().equals(id))
                            studentDao.findStudentById(studentId).getCourseList().delete(j);
                    }
                }
            }
            courseDao.delete(id);
        }
    }

    public Course findCourseById(String id) {
        if (courseDao.findAllCourses() != null) {
            LOGGER_INFO.info("find the course by id:" + id);
            return courseDao.findCourseById(id);
        } else {
            LOGGER_ERROR.error("List of courses is null!");
            System.out.println("List of courses is null!");
            return null;
        }
    }

    public MyArray<Course> findAllCourses() throws NullPointerException {
        if (courseDao.findAllCourses() != null) {
            LOGGER_INFO.info("find all courses");
            return courseDao.findAllCourses();
        } else {
            LOGGER_ERROR.error("List of courses is null!");
            System.out.println("List of courses is null!");
            return null;
        }
    }

    public MyArray<Student> findAllStudentsOfCourseByCourseId(String id) {
        if (courseDao.findCourseById(id) != null) {
            LOGGER_INFO.info("find all students of course by id:" + id);
            return courseDao.findCourseById(id).getStudentList();
        } else {
            LOGGER_ERROR.error("Course is null!");
            System.out.println("Course is null!");
            return null;
        }
    }
}