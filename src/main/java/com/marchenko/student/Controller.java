package com.marchenko.student;

import com.marchenko.student.domain.Student;
import com.marchenko.student.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPage(Map<String, Object> model) {
        Iterable<Student> persons= studentRepo.findAll();
        model.put("persons", persons);
        return "view";
    }

    @RequestMapping(value = "/main")
    public String EditingPage(Map<String, Object> model) {
        Iterable<Student> persons= studentRepo.findAll();
        model.put("persons", persons);
        return "redirect:/";
    }

    @RequestMapping(value = "/entities", method = RequestMethod.GET)
    public String EntitiesPage() {

        return "entities";
    }
    @GetMapping(value = {"/student/{studentId}/edit"})
    public String editStudent(Model model, @PathVariable Long studentId,@ModelAttribute("student") Student student)

    { return "redirect:/"; }
}