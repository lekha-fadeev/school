package ru.hogwarts.school.repository;

import ru.hogwarts.school.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
