package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public void deleteStudent(Long studentID) {
        boolean exists = studentRespository.existsById(studentID);
        if(!exists){
            throw new IllegalStateException(
                    "student does not exist"
            );
        }
        studentRespository.deleteById(studentID);

    }

    @Transactional
    public void updateStudent(Long studentID,
                              String name,
                              String email) {
        Student student = studentRespository.findById(studentID).orElseThrow(
                () -> new IllegalStateException(
                        "Student does not exist" + studentID
                )
        );

        if (name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
        }

    }
}

//        return List.of(
//                new Student(1L,
//                        "mark",
//                        "mark@gmail",
//                        LocalDate.of(2000, Month.MARCH,31),
//                        21
//                ));
