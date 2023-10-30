package ru.hogwarts.school.controller;

import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTestWebMvcTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentService studentService;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    void getStudentsByFacultyId() throws Exception {

        long facultyId = 1l;


        Student student1 = new Student("Odin", 1);
        Student student2 = new Student("Dva", 2);

        List<Student> students = Arrays.asList(student1, student2);

        when(studentService.getByFacultyId(facultyId)).thenReturn(students);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/students-by-faculty-id")
                                .param("id", String.valueOf(facultyId)))
                .andExpect(status().isOk())
                .andReturn();

        List<Student> actualStudents = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Student>>() {
        });
        assertEquals(students, actualStudents);
    }
}
