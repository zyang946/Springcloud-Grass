package com.generate.code.service.impl;

import com.generate.code.entity.Student;
import com.generate.code.repository.StudentRepository;
import com.generate.code.service.StudentService;
import com.generate.code.util.Response;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public StudentRepository StudentRepositoryInstance;

    @Override
    public Response getAllStudents(HttpHeaders headers) {
        List<Student> StudentList = StudentRepositoryInstance.findAll();
        if (StudentList.size() != 0)
            return new Response<>(1, "success", StudentList);
        return new Response<>(0, "empty", null);
    }

    @Override
    public Response createAndupdate(Student StudentDto, HttpHeaders headers) {
        StudentRepositoryInstance.save(StudentDto);
        return new Response<>(1, "success", StudentDto);
    }


    @Override
    public Response delete(Student StudentDto, HttpHeaders headers) {
        StudentRepositoryInstance.delete(StudentDto);
        return new Response<>(1, "success", StudentDto);
    }
    @Override
    public Response findById(String Id, HttpHeaders headers) {
        List<Student> Students = StudentRepositoryInstance.findBySid(Id);
        if (Students.size() != 0)
            return new Response<>(1, "success", Students);
        return new Response<>(0, "empty", Id);
    }
    @Override
    public Response deletById(String Id, HttpHeaders headers) {
        StudentRepositoryInstance.deleteById(Id);
        return new Response<>(1, "success", Id);
    }
    @Override
    public Response findByName(String Name, HttpHeaders headers) {
        List<Student> Students = StudentRepositoryInstance.findByName(Name);
        if (Students.size() != 0)
            return new Response<>(1, "success", Students);
        return new Response<>(0, "empty", Name);
    }
    @Override
    public Response deletByName(String Name, HttpHeaders headers) {
        StudentRepositoryInstance.deleteByName(Name);
        return new Response<>(1, "success", Name);
    }
}