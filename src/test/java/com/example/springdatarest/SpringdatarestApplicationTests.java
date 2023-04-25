package com.example.springdatarest;

import com.example.springdatarest.entity.Student;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDataRestApplicationIntegrationTests {



    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private  int port;



    @Test
    @Order(1)
    void testAddStudent() {
        String requestUrl = "http://localhost:"+port+"/api/student/"+"save";
        Student student = new Student("Hakim","Mokeddem","Alger","mokeddem.hakim@gmail.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student > request = new HttpEntity(student,headers);
        ResponseEntity<Student> response = testRestTemplate.exchange(requestUrl, HttpMethod.POST, request, Student.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }


    @Test
    @Order(2)
    void testGetStudents() {
        String requestUrl = "http://localhost:"+port+"/api/student/";
        Student student = new Student(1,"Hakim","Mokeddem","Alger","mokeddem.hakim@gmail.com");
        Student[] expectedList = { student};
        ResponseEntity<Student[]> response = testRestTemplate.exchange(requestUrl,HttpMethod.GET, null, Student[].class);
        assertArrayEquals(response.getBody(), expectedList);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    @Order(3)
    void testGetStudentById() {
        String requestUrl = "http://localhost:"+port+"/api/student/id=1";
        Student student = new Student(1,"Hakim","Mokeddem","Alger","mokeddem.hakim@gmail.com");
        ResponseEntity<Student> response = testRestTemplate.exchange(requestUrl,HttpMethod.GET, null, Student.class);
        assertEquals(response.getBody(), student);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    @Order(4)
    void testGetStudentsByFirstName() {
        String requestUrl = "http://localhost:"+port+"/api/student/firstname=Hakim";
        Student student = new Student(1,"Hakim","Mokeddem","Alger","mokeddem.hakim@gmail.com");
        Student[] expectedList = { student};
        ResponseEntity<Student[]> response = testRestTemplate.exchange(requestUrl,HttpMethod.GET, null, Student[].class);
        assertArrayEquals(response.getBody(), expectedList);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }


    @Test
    @Order(5)
    void testUpdateStudent() {
        String requestUrl = "http://localhost:"+port+"/api/student/update";
        Student student = new Student(1,"Hakim","Mokeddem","Algiers","mokeddem.hakim@yahoo.fr");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student > request = new HttpEntity(student,headers);
        ResponseEntity<Student> response = testRestTemplate.exchange(requestUrl, HttpMethod.PUT, request, Student.class);
        assertEquals(response.getBody(),student);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    @Order(5)
    void testDeleteStudent() {
        String requestUrl = "http://localhost:"+port+"/api/student/delete";
        Student student = new Student();
        student.setId(1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student > request = new HttpEntity(student,headers);
        ResponseEntity<String> response = testRestTemplate.exchange(requestUrl, HttpMethod.DELETE, request,String.class);
        assertEquals(response.getBody(),"Student Deleted");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }








}
