package com.example.spring.security.demo.controller;

import com.example.spring.security.demo.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    // Initialize with some hardcoded students
    public StudentController() {
        students.add(new Student(1, "Alice", 20));
        students.add(new Student(2, "Bob", 22));
        students.add(new Student(3, "Charlie", 19));
    }

    // GET /api/students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/csrf")
    public CsrfToken privateEndpoint(HttpServletRequest httpRequest) {
        return (CsrfToken) httpRequest.getAttribute("_csrf");
    }

    // POST /api/students
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}

