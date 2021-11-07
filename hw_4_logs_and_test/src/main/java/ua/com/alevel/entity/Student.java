package ua.com.alevel.entity;

import ua.com.alevel.util.MyArray;

public class Student {

    private String id;
    private String name;
    private String surName;
    private MyArray<Course> courseList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public MyArray<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(MyArray<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}
