//package ua.com.alevel.persistence.entity.search;
//
//import lombok.Getter;
//import lombok.Setter;
//import ua.com.alevel.persistence.entity.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "search_course_infos")
//public class SearchCourseInfo extends BaseEntity {
//
//    @Column(name = "count_teacher")
//    private Long countTeacher;
//
//    @Column(name = "count_course_name")
//    private Long countCourseName;
//
//    @Column(unique = true)
//    private String teacher;
//
//    @Column(unique = true)
//    private String courseName;
//
//    public SearchCourseInfo() {
//        super();
//        this.countCourseName = 0L;
//        this.countTeacher = 0L;
//    }
//}
