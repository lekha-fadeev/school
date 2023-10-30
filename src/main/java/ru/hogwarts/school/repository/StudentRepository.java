package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAgeBetween(int min, int max);

    List<Student> findByFacultyId(Long facultyId);

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM student",
            nativeQuery = true
    )
    Integer getCount();

    @Query(
            value = "SELECT AVG(s.age) " +
                    "FROM student s",
            nativeQuery = true
    )
    Double getAvgAge();

    @Query(
            value = "SELECT * " +
                    "FROM student " +
                    "ORDER BY id DESC " +
                    "LIMIT 5",
            nativeQuery = true
    )
    List<Student> getLastFive();

    @Query(
           value = "select s.age from Student s"
    )
    List<Integer> getAges();
}

