package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRespository studentRespository;

    @Autowired
    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }

    //logic for controller method GET
    public List<Student> getStudents(){

        //references the database connection Repository
        return studentRespository.findAll();
    }

    //logic for controller method POST
    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail = studentRespository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        studentRespository.save(student);

        //System.out.println(student);

    }
}

//        return List.of(
//                new Student(1L,
//                        "mark",
//                        "mark@gmail",
//                        LocalDate.of(2000, Month.MARCH,31),
//                        21
//                ));
