package ua.com.alevel.entity;

import ua.com.alevel.util.MyArray;

public class Course {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public MyArray<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(MyArray<Student> studentList) {
        this.studentList = studentList;
    }

    private String id;
    private String courseName;
    private int credit;
    private MyArray<Student> studentList;

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", studentList=" + studentList +
                '}';
    }
}