package ru.hogwarts.school.repository;

import ru.hogwarts.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
