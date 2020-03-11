package com.marchenko.student;

import com.marchenko.student.domain.Student;
import com.marchenko.student.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    public String main(Map<String, Object> model){
     Iterable<Student> persons= studentRepo.findAll();
        model.put("persons", persons);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name,@RequestParam String surname, @RequestParam Map<String, Object> model) {
        Student student = new Student(name,surname);
        studentRepo.save(student);
        Iterable<Student> persons = studentRepo.findAll();
        model.put("persons", persons);
        return "redirect:/";
    }

    @GetMapping(value = "/view")
    public String viewPage(Map<String, Object> model) {
        Iterable<Student> persons= studentRepo.findAll();
        model.put("persons", persons);
        return "view";
    }

    @GetMapping(value = "/main")
    public String EditingPage(Map<String, Object> model) {
        Iterable<Student> persons= studentRepo.findAll();
        model.put("persons", persons);
        return "redirect:/";
    }

    @GetMapping(value = "/entities")
    public String EntitiesPage() {

        return "entities";
    }

    @GetMapping(value = "/student/{studentId}")
    public String editStudent( @PathVariable Long studentId,Map<String, Object> model) {
        Optional<Student> student = studentRepo.findById(studentId);
        model.put("student", student);
        model.put("name",student.get().getName());
        model.put("surname",student.get().getSurname());

        return "student";
    }

    @PostMapping(value = {"/student/{studentId}"})
    public String updateUser(@PathVariable Long studentId,@ModelAttribute("student") Student student,@ModelAttribute("name") String name,@ModelAttribute("surname") String surname) {
        student.setId(studentId);
        student.setName(name);
        student.setSurname(surname);
        studentRepo.save(student);

        return "redirect:/";
    }
}