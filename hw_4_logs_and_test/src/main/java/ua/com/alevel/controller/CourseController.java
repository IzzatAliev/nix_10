package ua.com.alevel.controller;

import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.util.MyArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CourseController {

    private final CourseService courseService = new CourseService();

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    return;
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("Select your option");
        System.out.println("(1)Create course");
        System.out.println("(2)Update course");
        System.out.println("(3)Delete course");
        System.out.println("(4)FindById course");
        System.out.println("(5)FindAll course");
        System.out.println("(6)Find Student By CourseId");
        System.out.println("(0)Return to choose CRUD_operations");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "6" -> findAllStudentsByCourseId(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("CourseController.create");
        try {
            System.out.println("Enter the course name");
            String courseName = reader.readLine();
            System.out.println("Enter the number of credits");
            String credit = reader.readLine();
              MyArray<Student> studentMyArrayList = new MyArray<>();
            System.out.println("Enter the number of students");
            String count = reader.readLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.println("Enter the name of student");
                String name = reader.readLine();
                System.out.println("Enter the surname of student");
                String surName = reader.readLine();
                Student student = new Student();
                student.setName(name);
                student.setSurName(surName);
                studentMyArrayList.add(student);
            }

            Course course = new Course();
            course.setCourseName(courseName);
            course.setCredit(Integer.parseInt(credit));
            course.setStudentList(studentMyArrayList);
            courseService.create(course);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("CourseController.update");
        try {
            System.out.println("Enter CourseId");
            String id = reader.readLine();
            System.out.println("Enter the course name");
            String courseName = reader.readLine();
            System.out.println("Enter the number of credits");
            String credit = reader.readLine();
            Course course = new Course();
            course.setId(id);
            course.setCourseName(courseName);
            course.setCredit(Integer.parseInt(credit));
            courseService.update(course);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("CourseController.delete");
        try {
            System.out.println("Enter CourseId");
            String id = reader.readLine();
            courseService.deleteCourse(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("CourseController.findById");
        try {
            System.out.println("Enter CourseId");
            String id = reader.readLine();
            System.out.println(courseService.findCourseById(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        System.out.println("CourseController.findAll");
        MyArray<Course> courses = courseService.findAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i));
        }
    }

    private void findAllStudentsByCourseId(BufferedReader reader) {
        System.out.println("StudentController.findAllStudentsByCourseId");
        try {
            System.out.println("Enter CourseId");
            String id = reader.readLine();
            System.out.println(courseService.findAllStudentsOfCourseByCourseId(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}