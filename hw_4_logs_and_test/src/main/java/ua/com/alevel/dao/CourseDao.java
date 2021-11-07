package ua.com.alevel.dao;

import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Course;
import ua.com.alevel.util.MyArray;

public class CourseDao {

    public void create(Course course) {
        DBInMemory.getSample().createCourse(course);
    }

    public void update(Course course) {
        DBInMemory.getSample().updateCourse(course);
    }

    public void delete(String id) {
        DBInMemory.getSample().deleteCourse(id);
    }

    public Course findCourseById(String id) {
        return DBInMemory.getSample().findCourseById(id);
    }

    public MyArray<Course> findAllCourses() {
          return DBInMemory.getSample().findAllCourses();
     }

    public boolean existById(String id) {
        return DBInMemory.getSample().existCourseById(id);
    }
}