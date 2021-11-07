package ua.com.alevel.controller;

import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.MyArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentController {

    private final StudentService studentService = new StudentService();

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    break;
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
        System.out.println("(1)Create student");
        System.out.println("(2)Update student");
        System.out.println("(3)Delete student");
        System.out.println("(4)FindById student");
        System.out.println("(5)FindAll student");
        System.out.println("(6)Find Course By StudentId");
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
            case "6" -> findAllCoursesOfStudentByStudentId(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("StudentController.create");
        try {
            System.out.println("Enter the name");
            String name = reader.readLine();
            System.out.println("Enter the surname");
            String surName = reader.readLine();
            MyArray<Course> courseMyArrayList = new MyArray<>();
            System.out.println("Enter the number of courses");
            String count = reader.readLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.println("Enter the name of course");
                String CourseName = reader.readLine();
                System.out.println("Enter the number of course credits");
                String credit = reader.readLine();
                Course course = new Course();
                course.setCourseName(CourseName);
                course.setCredit(Integer.parseInt(credit));
                courseMyArrayList.add(course);
            }

            Student student = new Student();
            student.setName(name);
            student.setSurName(surName);
            student.setCourseList(courseMyArrayList);
            studentService.create(student);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("StudentController.update");
        try {
            System.out.println("Enter StudentId");
            String id = reader.readLine();
            System.out.println("Enter the name");
            String name = reader.readLine();
            System.out.println("Enter the surname");
            String surName = reader.readLine();
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setSurName(surName);
            studentService.update(student);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("StudentController.delete");
        try {
            System.out.println("Enter StudentId");
            String id = reader.readLine();
            studentService.deleteStudent(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("StudentController.findById");
        try {
            System.out.println("Enter StudentId");
            String id = reader.readLine();
            System.out.println(studentService.findStudentById(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        System.out.println("StudentController.findAll");
        MyArray<Student> students = studentService.findAllStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }

    private void findAllCoursesOfStudentByStudentId(BufferedReader reader) {
        System.out.println("StudentController.findAllCoursesOfStudentByStudentId");
        try {
            System.out.println("Enter StudentId");
            String id = reader.readLine();
            System.out.println(studentService.findAllCoursesOfStudentByStudentId(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}