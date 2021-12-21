package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.type.CourseType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseDaoImpl implements CourseDao {

    private final JpaConfig jpaConfig;

    public CourseDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private static final String CREATE_COURSE_QUERY = "insert into courses values(default, ?,?,?,?,?,?)";
    private static final String FIND_ALL_COURSES_QUERY = "select * from courses";
    private static final String FIND_COURSE_BY_ID_QUERY = "select * from courses where id = ";
    private static final String FIND_ALL_SIMPLE_COURSES_BY_STUDENT_ID_QUERY = "select id, course_name from courses left join student_course sc on courses.id = sc.course_id where sc.student_id = ";
    private static final String FIND_ALL_COURSES_BY_STUDENT_ID_QUERY = "select * from courses left join student_course sc on courses.id = sc.course_id where sc.student_id = ";

    @Override
    public void create(Course entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_COURSE_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getCourseName());
            preparedStatement.setInt(4, entity.getCredit());
            preparedStatement.setString(5, String.valueOf(entity.getCourseType()));
            preparedStatement.setString(6, entity.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Course entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Course findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_COURSE_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initCourseByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }


//    public DataTableResponse<Course> findAll(DataTableRequest request) {
//        List<Course> courses = new ArrayList<>();
//        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_COURSES_QUERY)) {
//            while (resultSet.next()) {
//                courses.add(initCourseByResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//        DataTableResponse<Course> dataTableResponse = new DataTableResponse<>();
//        dataTableResponse.setItems(courses);
//        return dataTableResponse;
//    }
    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        List<Course> courses = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, course_name, credit, course_type, description, count(course_id) as studentCount " +
                "from courses as course left join student_course as sc on course.id = sc.course_id " +
                "group by course.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                CourseResultSet courseResultSet = convertResultSetToSimpleCourse(resultSet);
                courses.add(courseResultSet.getCourse());
                otherParamMap.put(courseResultSet.getCourse().getId(), courseResultSet.getStudentCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Course> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(courses);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SIMPLE_COURSES_BY_STUDENT_ID_QUERY + studentId)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String courseName = resultSet.getString("course_name");
                map.put(id, courseName);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    @Override
    public List<Course> findAllByStudentId(Long studentId) {
        List<Course> courses = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_COURSES_BY_STUDENT_ID_QUERY + studentId)) {
            while (resultSet.next()) {
                courses.add(initCourseByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return courses;
    }

    private CourseResultSet convertResultSetToSimpleCourse(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String courseName = resultSet.getString("course_name");
        Integer credit = resultSet.getInt("credit");
        String courseType = resultSet.getString("course_type");
        String description = resultSet.getString("description");
        int studentCount = resultSet.getInt("studentCount");

        Course course = new Course();
        course.setId(id);
        course.setCourseName(courseName);
        course.setCredit(credit);
        course.setCourseType(CourseType.valueOf(courseType));
        course.setDescription(description);

        return new CourseResultSet(course, studentCount);
    }

    private Course initCourseByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        String courseName = resultSet.getString("course_name");
        Integer credit = resultSet.getInt("credit");
        String courseType = resultSet.getString("course_type");
        String description = resultSet.getString("description");

        Course course = new Course();
        course.setId(id);
        course.setCreated(created);
        course.setUpdated(updated);
        course.setCourseName(courseName);
        course.setCredit(credit);
        course.setCourseType(CourseType.valueOf(courseType));
        course.setDescription(description);

        return course;
    }

    private static class CourseResultSet {

        private final Course course;
        private final int studentCount;

        public CourseResultSet(Course course,int studentCount) {
            this.course = course;
            this.studentCount = studentCount;
        }

        public Course getCourse() {
            return course;
        }

        public int getStudentCount() {
            return studentCount;
        }
    }
}
