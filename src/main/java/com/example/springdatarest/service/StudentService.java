package com.example.springdatarest.service;


import com.example.springdatarest.entity.Student;
import com.example.springdatarest.repository.StudentRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        Student student = null;
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            student =  optionalStudent.get();
        }
        return student;
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return  studentRepository.findStudentsByFirstName(firstName);

    }

    public Student saveStudent(Student student) {
       return studentRepository.save(student);
    }

    public Student updateStudent(Student newStudent) {
       return studentRepository.findById(newStudent.getId()).map( student -> {
            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
           student.setAddress(newStudent.getAddress());
            student.setEmail(newStudent.getEmail());
            return studentRepository.save(student);
            }).orElseGet( () -> {
               return studentRepository.save(newStudent);
            });

    }

    public String deleteStudent(Student student) {
            if(studentRepository.findById(student.getId()).isPresent()) {
                studentRepository.deleteById(student.getId());
                return "Student Deleted";

            }
            return "Student Not Found";


    }
}
