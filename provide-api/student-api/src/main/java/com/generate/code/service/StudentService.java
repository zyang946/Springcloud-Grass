package com.generate.code.service;

import com.generate.code.entity.Student;
import com.generate.code.util.Response;
import org.apache.http.HttpHeaders;

public interface StudentService {
    public Response getAllStudents(HttpHeaders headers);
    public Response createAndupdate(Student Studentdto, HttpHeaders headers);
    public Response delete(Student Studentdto, HttpHeaders headers);
    public Response findById(String Id, HttpHeaders headers);
    public Response deletById(String Id, HttpHeaders headers);
    public Response findByName(String Name, HttpHeaders headers);
    public Response deletByName(String Name, HttpHeaders headers);
}
