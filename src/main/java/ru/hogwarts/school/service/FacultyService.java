package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyService(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    public Faculty add(String name, String color) {
        logger.info("Был вызван метод add");
        Faculty newFaculty = new Faculty(name, color);
        newFaculty = facultyRepository.save(newFaculty);
        return newFaculty;
    }

    public Faculty get(long id) {
        logger.info("Был вызван метод get");
        return facultyRepository.findById(id).get();
    }

    public Faculty update(long id, String name, String color) {
        logger.info("Был вызван метод update");
        Faculty facultyForUpdate = facultyRepository.findById(id).get();
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);
        return facultyRepository.save(facultyForUpdate);
    }

    public Faculty delete(long id) {
        logger.info("Был вызван метод delete");
        Faculty facultyForDelete = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return facultyForDelete;
    }

    public List<Faculty> getByColor(String color) {
        logger.info("Был вызван метод getByColor");
        return facultyRepository.findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Set<Faculty> getByColorOrNameIgnoreCase(String param) {
        logger.info("Был вызван метод getByColorOrNameIgnoreCase");
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findByColorIgnoreCase(param));
        result.addAll(facultyRepository.findByNameIgnoreCase(param));
        return result;
    }

    public List<Student> getStudentsByFacultyId(Long id) {
        logger.info("Был вызван метод getStudentsByFacultyId");
        return studentService.getByFacultyId(id);
    }

    public String getLongestName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max((name1, name2) -> name1.length() - name2.length())
                .get();
    }
}