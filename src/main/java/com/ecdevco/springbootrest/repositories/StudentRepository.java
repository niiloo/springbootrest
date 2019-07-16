package com.ecdevco.springbootrest.repositories;

import com.ecdevco.springbootrest.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
