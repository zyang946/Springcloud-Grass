package com.generate.code.controller;

import com.generate.code.entity.Student;
import com.generate.code.service.StudentService;
import com.generate.code.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService StudentServiceIntance;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @GetMapping ("/findAll")
    public ResponseEntity<Response> findAll(@RequestHeader HttpHeaders headers) {
            logger.info("find all Student");
        return ResponseEntity.ok(StudentServiceIntance.getAllStudents(headers));
    }

    @PostMapping("/createAndupdate")
    public ResponseEntity<Response> createAndupdate(@RequestBody Student Studentdto, @RequestHeader HttpHeaders headers) {
        logger.info("create or update new Student");
        return ResponseEntity.ok(StudentServiceIntance.createAndupdate(Studentdto, headers));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody Student Studentdto, @RequestHeader HttpHeaders headers) {
        logger.info("delete Student");
        return ResponseEntity.ok(StudentServiceIntance.delete(Studentdto, headers));
    }
    @GetMapping("/find/{Id}")
    public ResponseEntity<Response> findById(@PathVariable String Id, @RequestHeader HttpHeaders headers) {
        logger.info("find Student by Id");
         return ResponseEntity.ok(StudentServiceIntance.findById(Id, headers));
    }
     @DeleteMapping("/delete/{Id}")
     public ResponseEntity<Response> deleteById(@PathVariable String Id, @RequestHeader HttpHeaders headers) {
         logger.info("find Student by Id");
         return ResponseEntity.ok(StudentServiceIntance.deletById(Id, headers));
     }
    @GetMapping("/find/{Name}")
    public ResponseEntity<Response> findByName(@PathVariable String Name, @RequestHeader HttpHeaders headers) {
        logger.info("find Student by Name");
         return ResponseEntity.ok(StudentServiceIntance.findByName(Name, headers));
    }
     @DeleteMapping("/delete/{Name}")
     public ResponseEntity<Response> deleteByName(@PathVariable String Name, @RequestHeader HttpHeaders headers) {
         logger.info("find Student by Name");
         return ResponseEntity.ok(StudentServiceIntance.deletByName(Name, headers));
     }
}