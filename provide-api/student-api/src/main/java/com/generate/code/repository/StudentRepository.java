package com.generate.code.repository;

import com.generate.code.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends CrudRepository<Student, String> {
    List<Student> findAll();
    void delete(Student Studentdto);
    List<Student> findBySid(String Id);
    void deleteById(String Id);
    List<Student> findByName(String Name);
    void deleteByName(String Name);
}