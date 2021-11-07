package ua.com.alevel.db;

import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.MyArray;

import java.util.UUID;

public class DBInMemory {

    private static DBInMemory sample;
    private final MyArray<Student> students = new MyArray<>();
    private final MyArray<Course> courses = new MyArray<>();

    private DBInMemory() { }

    public static DBInMemory getSample() {
        if (sample == null) {
            sample = new DBInMemory();
        }
        return sample;
    }

    private enum Entity {
        STUDENT, COURSE
    }

    public void createStudent(Student student) {
        student.setId(generateId(Entity.STUDENT));
        students.add(student);
    }

    public void createCourse(Course course) {
        course.setId(generateId(Entity.COURSE));
        courses.add(course);
    }

    public void updateStudent(Student student) {
        Student inDbStudent = findStudentById(student.getId());
        inDbStudent.setName(student.getName());
        inDbStudent.setSurName(student.getSurName());
    }

    public void updateCourse(Course course) {
        Course inDbCourse = findCourseById(course.getId());
        inDbCourse.setCourseName(course.getCourseName());
        inDbCourse.setCredit(course.getCredit());
    }

    public void deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.delete(i);
            }
        }
    }

    public void deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                courses.delete(i);
            }
        }
    }

    public Student findStudentById(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return students.get(i);
            }
        }
        return null;
    }

    public Course findCourseById(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return courses.get(i);
            }
        }
        return null;
    }

        public MyArray<Student> findAllStudents() {
            return students;
        }
        public MyArray<Course> findAllCourses() {
            return courses;
      }

    public boolean existStudentById(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean existCourseById(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String generateId(Entity entity) {
        String id = UUID.randomUUID().toString();
        switch (entity) {
            case STUDENT -> {
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId().equals(id)) {
                        return generateId(entity);
                    }
                }
            }
            case COURSE -> {
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId().equals(id)) {
                        return generateId(entity);
                    }
                }
            }
        }
        return id;
    }
}