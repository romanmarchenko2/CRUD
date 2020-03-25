package com.marchenko.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.marchenko.student.model.Hobby;
import com.marchenko.student.model.Student;
import com.marchenko.student.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepo repository;

    public List<Student> getAllStudents()
    {
        List<Student> result = (List<Student>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Student>();
        }
    }

    public Student getStudentsById(Long id)
    {
        Optional<Student> student = repository.findById(id);

        if(student.isPresent()) {
            return student.get();
        } else {
            throw new IllegalArgumentException("No student record exist for given id");
        }
    }

    public Student createOrUpdateEmployee(Student oneStudent)
    {
        if(oneStudent.getId()  == null)
        {
            oneStudent = repository.save(oneStudent);

            return oneStudent;
        }
        else
        {
            Optional<Student> student = repository.findById(oneStudent.getId());

            if(student.isPresent())
            {
                Student newStudent = student.get();
                Hobby hobby=null;
                newStudent.setName(oneStudent.getName());
                newStudent.setSurname(oneStudent.getSurname());
                newStudent.setHobby(oneStudent.getHobby());
                newStudent = repository.save(newStudent);
                return newStudent;
            } else {
                oneStudent = repository.save(oneStudent);

                return oneStudent;
            }
        }
    }

    public void deleteStudentById(Long id) throws IllegalArgumentException
    {
        Optional<Student> student = repository.findById(id);

        if(student.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No student record exist for given id");
        }
    }
}