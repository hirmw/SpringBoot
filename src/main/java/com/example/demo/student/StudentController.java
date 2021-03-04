package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/students")

public class StudentController {

    private final StudentService studentService;

    //instanitates the studentService class
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService =  studentService;
    }


    //provides GET method
    //GET -> studentservice method -> calls methods held in repositories
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();

    }

    //provides Post method
    //POST -> studentservice method -> calls methods held in repositories
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

}
