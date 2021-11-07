package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.MyArray;

public class StudentService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final StudentDao studentDao = new StudentDao();
    private final CourseDao courseDao = new CourseDao();

    public void create(Student student) {
        LOGGER_INFO.info("create the new student: " + student.getName());
        studentDao.create(student);
        Student student1 = new Student();
        student1.setId(student.getId());
        student1.setName(student.getName());
        student1.setSurName(student.getSurName());
        MyArray<Student> s = new MyArray<>();
        s.add(student1);
        if (student.getCourseList() != null) {
            for (int i = 0; i < student.getCourseList().size(); i++) {
                student.getCourseList().get(i).setStudentList(s);
                Course course = student.getCourseList().get(i);
                if (!courseDao.existById(course.getId())) {
                    LOGGER_INFO.info("create the new course: " + course.getCourseName());
                    courseDao.create(course);
                }
            }
        }
    }

    public void update(Student student) {
        LOGGER_INFO.info("update the student: " + student.getName());
        if (studentDao.existById(student.getId())) {
            studentDao.update(student);
            String studentId = student.getId();
            student.setCourseList(studentDao.findStudentById(studentId).getCourseList());
            Student thisStudent = studentDao.findStudentById(studentId);
            if (thisStudent.getCourseList() != null) {
                for (int i = 0; i < thisStudent.getCourseList().size(); i++) {
                    String courseId = thisStudent.getCourseList().get(i).getId();
                    for (int j = 0; j < courseDao.findCourseById(courseId).getStudentList().size(); j++) {
                        if (courseDao.findCourseById(courseId).getStudentList().get(j).getId().equals(studentId))
                            courseDao.findCourseById(courseId).getStudentList().update(j, student);
                    }
                }
            }
        }
    }

    public void deleteStudent(String id) {
        LOGGER_WARN.warn("remove the student by id: " + id);
        if (studentDao.existById(id)) {
            Student thisStudent = studentDao.findStudentById(id);
            if (thisStudent.getCourseList() != null) {
                for (int i = 0; i < thisStudent.getCourseList().size(); i++) {
                    String courseId = thisStudent.getCourseList().get(i).getId();
                    for (int j = 0; j < courseDao.findCourseById(courseId).getStudentList().size(); j++) {
                        if (courseDao.findCourseById(courseId).getStudentList().get(j).getId().equals(id))
                            courseDao.findCourseById(courseId).getStudentList().delete(j);
                    }
                }
            }
            studentDao.delete(id);
        }
    }

    public Student findStudentById(String id) {
        if (studentDao.findAllStudents() != null) {
            LOGGER_INFO.info("find the student by id:" + id);
            return studentDao.findStudentById(id);
        } else {
            LOGGER_ERROR.error("List of students is null!");
            System.out.println("List of students is null!");
            return null;
        }
    }

    public MyArray<Student> findAllStudents() {
        LOGGER_INFO.info("find all students");
        if (studentDao.findAllStudents() != null) {
            return studentDao.findAllStudents();
        } else {
            LOGGER_ERROR.error("List of students is null!");
            System.out.println("List of students is null!");
            return null;
        }
    }

    public MyArray<Course> findAllCoursesOfStudentByStudentId(String id) {
        if (studentDao.findStudentById(id) != null) {
            LOGGER_INFO.info("find all courses of student by id:" + id);
            return studentDao.findStudentById(id).getCourseList();
        } else {
            LOGGER_ERROR.error("Student is null!");
            System.out.println("Student is null!");
            return null;
        }
    }
}