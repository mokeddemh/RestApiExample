package com.example.springdatarest.controller;


import com.example.springdatarest.entity.EmptyJsonResponse;
import com.example.springdatarest.entity.Student;
import com.example.springdatarest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity findAllStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/id={id}")
    public ResponseEntity findStudentById(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(new EmptyJsonResponse());

        }
    }

    @GetMapping("/firstname={firstName}")
    public ResponseEntity findStudentsByFirstName(@PathVariable String firstName) {
         return ResponseEntity.ok(studentService.getStudentsByFirstName(firstName));
    }

    @PostMapping("/save")
    public ResponseEntity saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));

    }

    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestBody Student student) {

        return ResponseEntity.ok(studentService.deleteStudent(student));
    }



}
