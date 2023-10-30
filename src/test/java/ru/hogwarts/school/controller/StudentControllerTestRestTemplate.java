package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Assertions;
import ru.hogwarts.school.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    void create_success() throws Exception {

        Student studentForCreate = new Student("Иван", 20);

        Student expectedStudent = new Student("Иван", 20);

        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        assertThat(postedStudent).isNotNull();
        assertEquals(expectedStudent.getName(), postedStudent.getName());
        assertEquals(expectedStudent.getAge(), postedStudent.getAge());
    }

    @Test
    void get() throws Exception {

        Student studentForCreate = new Student("Иван", 20);

        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        Student actualStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + postedStudent.getId(), Student.class);
        assertEquals(postedStudent, actualStudent);
    }

    @Test
    void getByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/by-age",String.class))
                .isNotNull();
    }

    @Test
    void getByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/by-age-between",String.class))
                .isNotNull();
    }

    @Test
    void getFacultyByStudentId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty-by-student-id",String.class))
                .isNotNull();
    }
}