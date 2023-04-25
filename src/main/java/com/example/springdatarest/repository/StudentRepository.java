package com.example.springdatarest.repository;

import com.example.springdatarest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student > findStudentsByFirstName(String firstName);
}
