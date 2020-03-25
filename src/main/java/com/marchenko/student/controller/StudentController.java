package com.marchenko.student.controller;

import com.marchenko.student.model.Student;
import com.marchenko.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class StudentController
{
    @Autowired
    StudentService service;

    @RequestMapping
    public String getAllStudents( Model model)
    {
        List<Student> list = service.getAllStudents();
        model.addAttribute("students", list);
        return "list-students";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editStudentById(Model model,@PathVariable("id") Optional<Long> id)
    {
        if (id.isPresent()) {
            Student student = service.getStudentsById(id.get());
            model.addAttribute("student", student);
        } else {
            model.addAttribute("student", new Student());
        }
        return "add-edit-student";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteStudentById(Model model, @PathVariable("id") Long id)
    {
        service.deleteStudentById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createStudent", method = RequestMethod.POST)
    public String createOrUpdateEmployee(@Valid Student student, BindingResult bindingResult)
    {  if (bindingResult.hasErrors()) {
        return "add-edit-student";
    }
    service.createOrUpdateEmployee(student);

        return "redirect:/";
    }
}
