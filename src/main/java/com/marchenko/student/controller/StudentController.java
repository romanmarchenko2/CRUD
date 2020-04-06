package com.marchenko.student.controller;

import com.marchenko.student.model.Student;
import com.marchenko.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class StudentController
{
    @Autowired
    StudentService service;

    @RequestMapping
    public  ResponseEntity<List<Student>> getAllStudents( Model model)
    {
        List<Student> list = service.getAllStudents();
        if (list.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public ResponseEntity<?>editStudentById(Model model,@PathVariable("id") Optional<Long> id)
    {Student student;
        if (id.isPresent()) {
            student = service.getStudentsById(id.get());
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteStudentById(Model model, @PathVariable("id") Long id)
    {
        service.deleteStudentById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/createStudent", method = RequestMethod.POST)
    public ResponseEntity<?> createOrUpdateEmployee(@RequestBody @Valid Student student, BindingResult bindingResult, UriComponentsBuilder ucBuilder)
    {  if (bindingResult.hasErrors())
    {
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    service.createOrUpdateEmployee(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
