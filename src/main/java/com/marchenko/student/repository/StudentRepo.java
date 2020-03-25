package com.marchenko.student.repository;

import com.marchenko.student.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends CrudRepository<Student,Long> {

}
