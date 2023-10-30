package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Assertions;
import ru.hogwarts.school.entity.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestRestTemplate {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    void create_success() throws Exception {

        Faculty facultyForCreate = new Faculty("Гриф", "red");

        Faculty expectedFaculty = new Faculty("Гриф", "red");

        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/student", facultyForCreate, Faculty.class);
        assertThat(postedFaculty).isNotNull();
        assertEquals(expectedFaculty.getName(), postedFaculty.getName());
        assertEquals(expectedFaculty.getColor(), postedFaculty.getColor());
    }

    @Test
    void get() throws Exception {

        Faculty facultyForCreate = new Faculty("Гриф", "red");

        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/student", facultyForCreate, Faculty.class);
        Faculty actualFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + postedFaculty.getId(), Faculty.class);
        assertEquals(postedFaculty, actualFaculty);
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
